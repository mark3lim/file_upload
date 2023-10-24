package userReview.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import summary.vo.SummaryVO;

public class UserReviewDAO {
	
	
	private static UserReviewDAO uDAO;
	
	private UserReviewDAO () {
		
	}
	
	public static UserReviewDAO getInstance() {
		if(uDAO==null) {
			uDAO=new UserReviewDAO();
		}
		return uDAO;
	}
	
	
	public void insertReview() {
		
		
	}
	
	public void updateReview() {
		
	}
	
	public void deleteReview() {
		
	}
	
	public List<SummaryVO> selectAllReview(String id) throws SQLException {
		
		List<SummaryVO> list = new ArrayList<SummaryVO>();
		
		DbConnection db= DbConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		
		
		
		
		try {
			con=db.getConn("jdbc/dbcp");
			
			StringBuilder selectAllReview = new StringBuilder();

			selectAllReview.append(" select  m.name, r.REV_CONT, r.star , r.REV_DATE, r.r_view, r.rcode ,c.cat_name from member m, review r,goods g, category c where m.id=r.id and g.gcode=r.gcode and c.cat_code = g.cat_code and m.id= ? ");
			
			pstmt=con.prepareStatement(selectAllReview.toString());
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			SummaryVO sVO= null;
			while(rs.next()) {
				sVO= new SummaryVO();
				sVO.setName(rs.getString("name"));
				sVO.setCategory(rs.getString("cat_name"));
				sVO.setReview(rs.getString("REV_CONT"));
				sVO.setReviewDate(rs.getString("REV_DATE"));
				sVO.setView(rs.getInt("r_view"));
				sVO.setRcode(rs.getInt("rcode"));
				sVO.setStar(rs.getInt("star"));
				list.add(sVO);
			}
		}finally {
			db.dbClose(rs, pstmt, con);
			
		}
		
		return list;
	}
	
public SummaryVO selectOneReview(int rcode) throws SQLException {
		
		
		SummaryVO sVO= null;
		
		DbConnection db= DbConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		
		
		
		
		try {
			con=db.getConn("jdbc/dbcp");
			
			String selectAllReview = "select distinct m.name, r.REV_CONT,  r.star, r.rcode from member m, review r, category c where m.id=r.id   and r.rcode= ? ";

			
			
			pstmt=con.prepareStatement(selectAllReview);
			
			pstmt.setInt(1, rcode);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				sVO= new SummaryVO();
				sVO.setName(rs.getString("name"));
				sVO.setReview(rs.getString("REV_CONT"));
				sVO.setRcode(rs.getInt("rcode"));
				sVO.setStar(rs.getInt("star"));
			
			}
		}finally {
			db.dbClose(rs, pstmt, con);
			
		}
		
		return sVO;
	}
	
	
}
