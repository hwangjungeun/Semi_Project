package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import board.model.InterBoardDAO;
import board.model.InterNoticeDAO;
import board.model.NoticeDAO;
import board.model.NoticeVO;
import common.controller.AbstractController;

public class QnaReplyEndAction extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
        
        if("post".equalsIgnoreCase(method)) { // 수정할 글내용과 제목등을 적고 수정하기버튼을 눌러야 POST로 들어올 수 있다.
           
        	String board_num = request.getParameter("board_num");
        	String board_id = request.getParameter("board_id");
	        String board_subject = request.getParameter("board_subject");
	        String board_content = request.getParameter("board_content");
	        String board_date = request.getParameter("board_date");
	        String board_count = request.getParameter("board_count");
	        
            InterBoardDAO bdao = new BoardDAO();
            bdao.writeReply(board_id, board_subject, board_content, board_num);
          	
          	request.setAttribute("board_num", board_num);
          	request.setAttribute("board_id", board_id);
          	request.setAttribute("board_subject", board_subject);
          	request.setAttribute("board_content", board_content);
	        request.setAttribute("board_date", board_date);
	        request.setAttribute("board_count", board_count);
	         
          	String message = "";
	        String loc = "";
           

	            if(board_id!=null) {// 답글 등록 성공
	            	
	            	BoardVO bvo = bdao.writeReplyEnd(board_id, board_subject, board_content, board_num, board_date, board_count);
	            	
	            	message = "답글 등록 성공!!";
	            	request.setAttribute("bvo", bvo);
	            	
	            	super.setViewPage("/WEB-INF/board/qnaReplyEnd.jsp");
	                
	            }
	            else {// 답글 등록 실패
	               message = "답글 등록 실패!!";
	               loc = "javascript:history.back()";
	               
	               request.setAttribute("message", message);
			       request.setAttribute("loc", loc);
			        
			       super.setViewPage("/WEB-INF/msg.jsp");
	            }
           
	            
	         }
        
			else {
				
			// **** POST 방식으로 넘어온 것이 아니라면 **** //
			
			String message = "비정상적인 경로를 통해 들어왔습니다.!!";
	        String loc = "javascript:history.back()";
	         
	        request.setAttribute("message", message);
	        request.setAttribute("loc", loc);
	         
	        super.setViewPage("/WEB-INF/msg.jsp");
	         
           
        }
        
        
  }


}
