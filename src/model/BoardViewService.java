package model;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.BoardDAO;
import dao.NoticeDao;
import dto.Notice;

public class BoardViewService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("nNo"));
		Long nNo = Long.parseLong( opt.orElse("0") );
		
		
		HttpSession session = request.getSession();
		
		// 조회수 증가
		if (session.getAttribute("open") == null) {
			session.setAttribute("open", true);
			BoardDAO.getInstance().updateBoardHit(no);
		}
		
		Board board = BoardDAO.getInstance().selectBoardView(no);
		
		if (board != null) {
			session.setAttribute("board", board);
			
			List<Reply> replyList = ReplyDao.getInstance().selectReplyList(no);
			
			request.setAttribute("board", board);
			
			return new ModelAndView("board/view.jsp", false);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글이 등록 되지 않았습니다..')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
	
	}

}
