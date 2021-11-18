package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Member;
import dto.Notice;
import mybatis.config.DBService;

public class BoardDAO {

	/* StudentDao의 모든 메소드는 factory에서 SqlSession을 얻어 낸다. */
	private SqlSessionFactory factory;
	
	private static BoardDAO instance;
	private BoardDAO() {
		factory = DBService.getInstance().getFactory();
	}
	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	public List<Board> selectBoardList() {
		SqlSession ss = factory.openSession();
		List<Board> list = ss.selectBoardList("dao.board.selectBoardList");
		ss.close();
		return list;
	}
	
	public Board selectBoardView(Board board) {
		   SqlSession ss = factory.openSession();
		   Board board =  ss.selectOne("dao.board.selectBoard", board);
		   ss.close();
		   return board;
	   }
	
	
	public int updateBoardHit(Long nNo) {    // 상세보기 할 때~~
		SqlSession ss = factory.openSession(false);
		int result = ss.update("dao.board.updateboardHit", nNo);
		if (result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	public int insertBoard(Board board) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("dao.board.insertNotice", board);
		if (result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	public int updateBoard(Board board) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("dao.board.updateBoard", board);
		if (result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	public int deleteBoard(Long nNo) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete("dao.Board.deleteBoard", nNo);
		if (result > 0) ss.commit();
		ss.close();
		return result;
	}
	
}
