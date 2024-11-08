package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.sql.DataSource;

import jakarta.servlet.ServletContext;
import model.PagingUtil;
import service.DaoService;

	public class BoardDao implements DaoService<BoardDto> {

		//필드]
		private Connection conn;
		private ResultSet rs;
		private PreparedStatement psmt;
		
		//생성자
		public BoardDao(ServletContext context) {
			try {
				DataSource source=(DataSource)context.getAttribute("DATA-SOURCE");
				conn= source.getConnection();
			}
			catch(SQLException e) {e.printStackTrace();}
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
			catch(SQLException e) {e.printStackTrace();}
			
		}////////////////
		@Override
		public List<BoardDto> findAll(Map<String, String> map) {
			List<BoardDto> items = new Vector<>();
			//페이징 적용 쿼리
			String sql ="SELECT * FROM (SELECT d.*,RANK() OVER (ORDER BY id DESC) AS idRank FROM board d ";			
			sql+=")  WHERE idRank BETWEEN ? AND ? ";
			try {
				psmt = conn.prepareStatement(sql);
				
				//페이징을 위한 시작 및 종료 Rank설정
				psmt.setString(1, map.get(PagingUtil.START));
				psmt.setString(2, map.get(PagingUtil.END));			
				rs = psmt.executeQuery();
			
			      while (rs.next()) {
		                BoardDto item = new BoardDto();
		                // 필요한 모든 필드를 설정
		                item.setId(rs.getLong("id"));
		                item.setTitle(rs.getString("title"));
		                item.setContent(rs.getString("content"));
		                item.setUsername(rs.getString("username"));
		                item.setPostDate(rs.getDate("postDate"));
		                items.add(item);
			      }
			      
			}
			      
		    catch(SQLException e) {e.printStackTrace();}
			System.out.println("size:"+items.size());
			return items;
		}
			
		@Override
		public BoardDto findById(String... params) {
			BoardDto item=null;
			try {
				psmt=conn.prepareStatement("SELECT * FROM board WHERE id=?");
				psmt.setString(1, params[0]);
				rs= psmt.executeQuery();
				if(rs.next()) {
					item = new BoardDto();
					item.setContent(rs.getString(3));	
					item.setId(rs.getLong(1));		
					item.setPostDate(rs.getDate(4));
					item.setTitle(rs.getString(2));
					item.setUsername(rs.getString(5));
				}
			}
			catch(SQLException e) {e.printStackTrace();}
			return item;
		}
		@Override
		public int getTotalRecordCount(Map<String, String> map) {
			int totalRecordCount=0;
			String sql="SELECT COUNT(*) FROM board ";
			
			try {
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				rs.next();
				totalRecordCount= rs.getInt(1);
			}
			catch(SQLException e) {e.printStackTrace();}
			return totalRecordCount;
		}
		@Override
		 public int insert(BoardDto item) {
				int affected=0;
				try {
					psmt = conn.prepareStatement("INSERT INTO board VALUES (seq_board.nextval, ?, ?,SYSDATE, ?)");
		            psmt.setString(1, item.getTitle());
		            psmt.setString(2, item.getContent());
		            psmt.setString(3, item.getUsername());
		            affected = psmt.executeUpdate();
				}
				catch(SQLException e) {e.printStackTrace();}
				return affected;
			}

		@Override
		public int update(BoardDto dto) {
			int affected=0;
			try {
				psmt = conn.prepareStatement("UPDATE board SET title=?,content=?,username=?  WHERE id=?");

				psmt.setString(1, dto.getTitle());
				psmt.setString(2, dto.getContent());
				psmt.setString(3, dto.getUsername());
				psmt.setLong(4, dto.getId());
				affected=psmt.executeUpdate();
			}
			catch(SQLException e) {e.printStackTrace();}
			return affected;
		}
		@Override
		public int delete(BoardDto dto) {
			int affected=0;
			try {
				psmt = conn.prepareStatement("DELETE board WHERE id=?");
				psmt.setLong(1, dto.getId());		
				affected=psmt.executeUpdate();
			}
			catch(SQLException e) {e.printStackTrace();}
			return affected;
		}
	
		//비밀번호 확인용]
		public boolean existsPasswordById(String id, String password) {
			try {
				psmt= conn.prepareStatement("SELECT COUNT(*) FROM board WHERE id=? AND password=?");
				psmt.setString(1, id);
				psmt.setString(2, password);
				rs = psmt.executeQuery();
				rs.next();
				if(rs.getInt(1)==1) return true;
			}
			catch(SQLException e) {e.printStackTrace();}
			return false;
		}
		public void updateDownCount(String id) {
			try {
				psmt = conn.prepareStatement("UPDATE board SET downcount=downcount+1 WHERE id=?");
				psmt.setString(1, id);		
				psmt.executeUpdate();
			}
			catch(SQLException e) {e.printStackTrace();}
		}
	}
	
