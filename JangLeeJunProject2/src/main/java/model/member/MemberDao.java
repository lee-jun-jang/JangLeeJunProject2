package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.sql.DataSource;

import jakarta.servlet.ServletContext;
import model.PagingUtil;
import service.DaoService;

	public class MemberDao implements DaoService<MemberDto> {

		//필드]
		private static MemberDao mDao;
		private Connection conn;
		private ResultSet rs;
		private PreparedStatement psmt;
		
		//생성자
		public MemberDao(ServletContext context) {
			try {
				//커넥션 풀 사용.즉 커넥션 풀에서 커넥션 객체 가져오기
				//(리스너에서 컨텍스트 영역에 저장한 데이타소스 가져오기)
				DataSource source=(DataSource)context.getAttribute("DATA-SOURCE");
				conn= source.getConnection();
			}
			catch(SQLException e) {e.printStackTrace();}
		}
		public MemberDao() {
		
		}
		//자원반납용-사용한 커넥션 객체를 다시 풀에 반납
		@Override
		public void close() {
			try {
				//메모리 해제
				if(rs !=null) rs.close();
				if(psmt !=null) psmt.close();
				//커넥션 풀에 커넥션 객체 반납-메모리 해제 아님]
				if(conn !=null) conn.close();
			} 
			catch(SQLException e) {}
			
		}

		public static synchronized MemberDao getInstance() {
			if (mDao == null) {
				mDao = new MemberDao();
			}
			return mDao;
		}
		
		@Override
		public List<MemberDto> findAll(Map<String, String> map) {
			List<MemberDto> items = new Vector<>();
			//페이징 적용 쿼리
			String sql ="SELECT * FROM (SELECT d.*,RANK() OVER (ORDER BY id DESC) AS idRank FROM member d ";
			
			sql+=")  WHERE idRank BETWEEN ? AND ? ";
			
			try {
				psmt = conn.prepareStatement(sql);
				
				//페이징을 위한 시작 및 종료 Rank설정
				psmt.setString(1, map.get(PagingUtil.START));
				psmt.setString(2, map.get(PagingUtil.END));			
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					MemberDto item = new MemberDto();
					item.setName(rs.getString(3));
					item.setPassword(rs.getString(2));
					item.setUsername(rs.getString(1));
					item.setGender(rs.getString(4));
					item.setInfo(rs.getString(7));
					item.setInter(rs.getString(5));
					item.setGrade(rs.getString(6));
					item.setRegiDate(rs.getDate(8));					
					items.add(item);
				}
			}
			catch(SQLException e) {e.printStackTrace();}
			System.out.println("size:"+items.size());
			return items;
		}
		@Override
		public MemberDto findById(String... params) {
			MemberDto user = null;
	        try {
	            String sql = "SELECT * FROM member WHERE username=?";
	            psmt = conn.prepareStatement(sql);
	            psmt.setString(1, user.getUsername());
	            rs = psmt.executeQuery();
	            if (rs.next()) {
	                user = new MemberDto();
	                user.setUsername(rs.getString("username"));
	                user.setPassword(rs.getString("password"));
	                user.setName(rs.getString("name"));
	                user.setGender(rs.getString("gender"));
	                user.setInter(rs.getString("inter"));
	                user.setGrade(rs.getString("grade"));
	                user.setInfo(rs.getString("info"));
	                user.setRegiDate(rs.getDate("regiDate"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return user;
	    }
		
		@Override
		public int getTotalRecordCount(Map<String, String> map) {
			int totalRecordCount=0;
			String sql="SELECT COUNT(*) FROM board b JOIN member u ON b.username=u.username ";
			//검색시 아래 쿼리 추가
			if(map !=null && map.get("searchColumn") !=null) {
				sql+=" WHERE "+map.get("searchColumn") + " LIKE '%"+map.get("searchWord")+"%' ";
			}
			try {
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				rs.next();
				totalRecordCount= rs.getInt(1);
			}
			catch(SQLException e) {e.printStackTrace();}
			return totalRecordCount;
		}//////////////////
		@Override
		public int insert(MemberDto dto) {
			int affected=0;
			try {
				psmt = conn.prepareStatement("INSERT INTO member VALUES(?,?,?,?,?,?,?,DEFAULT)");
				psmt.setString(1, dto.getUsername());
				psmt.setString(2, dto.getPassword());
				psmt.setString(3, dto.getName());
				psmt.setString(4, dto.getGender());
				psmt.setString(5, dto.getInter());
				psmt.setString(6, dto.getGrade());
				psmt.setString(7, dto.getInfo());
				affected=psmt.executeUpdate();
			}
			catch(SQLException e) {e.printStackTrace();}

		return affected;
		}
		@Override
		public int update(MemberDto dto) {
			int affected=0;
			try {
				psmt = conn.prepareStatement("UPDATE member SET password=?,name=?,gender=?,inter=?,grade=?,info=?,DEFAULT  WHERE username=?");
				psmt.setString(1, dto.getPassword());
				psmt.setString(2, dto.getName());
				psmt.setString(3, dto.getGender());
				psmt.setString(4, dto.getInter());
				psmt.setString(5, dto.getGrade());
				psmt.setString(6, dto.getInfo());
				affected=psmt.executeUpdate();
			}
			catch(SQLException e) {e.printStackTrace();}
			return affected;
		}
		@Override
		public int delete(MemberDto dto) {
			int affected=0;
			try {
				psmt = conn.prepareStatement("DELETE member WHERE username=?");
				psmt.setString(1, dto.getUsername());		
				affected=psmt.executeUpdate();
			}
			catch(SQLException e) {e.printStackTrace();}
			return affected;
		}
		public boolean isUser(String username,String password) {
			try {
				psmt = conn.prepareStatement("SELECT COUNT(*) FROM member WHERE username=? AND password=?");
				psmt.setString(1, username);
				psmt.setString(2, password);
				rs = psmt.executeQuery();
				rs.next();
				if(rs.getInt(1)==0) return false;
			
			}
			catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}
