package model_reply;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.ReplyDao;
import dto.Reply;

public class ReplyInsertService implements ReplyService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// writer content nNo 받아야함
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		Long nNo = Long.parseLong(request.getParameter("nNo"));
		
		String ip = request.getRemoteAddr();
		
		Reply reply = new Reply();
		reply.setauthor(author);
		reply.setContent(content);
		reply.setno(no);
		reply.setIp(ip);
		
		int result = ReplyDao.getInstance().insertReply(reply);
		
		// 삽입 성공
		if (result > 0) {
			return new ModelAndView("view.board?no=" + no, true);
		} else {  // 실패
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 달기 실패')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		
		
		
	}

}
