package model;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dao.NoticeDao;

public class BoardDeleteService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		Long no = Long.parseLong(request.getParameter("no"));
		
		
		// 실행
		Long no = Long.parseLong(request.getParameter("no"));
		
		List<Reply> replyList = ReplyDao.getInstance().selectReplyList(no);
		
		// 삭제
		PrintWriter out = response.getWriter();
		if (replyList.size() == 0) {
			
			int result = BoardDao.getInstance().deleteNotice(no);  
			
			if (result > 0) {
				out.println("<script>");
				out.println("alert('공지사항 삭제 성공')");
				out.println("location.href='list.board'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('공지사항 수정 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} 
		
		else {
			out.println("<script>");
			out.println("alert('댓글이 달린 게시글은 삭제할 수 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
	
	}

}
