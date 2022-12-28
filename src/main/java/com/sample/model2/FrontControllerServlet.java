package com.sample.model2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.sample.app.controller.HomeController;
import com.sample.app.controller.post.DeleteCommentController;
import com.sample.app.controller.post.DeleteController;
import com.sample.app.controller.post.DetailController;
import com.sample.app.controller.post.FormController;
import com.sample.app.controller.post.InsertCommentController;
import com.sample.app.controller.post.InsertController;
import com.sample.app.controller.post.ListController;
import com.sample.app.controller.post.ModifyController;
import com.sample.app.controller.post.ModifyFormController;
import com.sample.app.controller.post.ReadController;
import com.sample.app.controller.todo.TodoAddWorkController;
import com.sample.app.controller.todo.TodoDetailController;
import com.sample.app.controller.todo.TodoListController;
import com.sample.app.controller.todo.TodoModifyController;
import com.sample.app.controller.todo.TodoModifyFormController;
import com.sample.app.controller.todo.TodoRegisterController;
import com.sample.app.controller.todo.TodoRegisterFormController;
import com.sample.app.controller.user.LoginController;
import com.sample.app.controller.user.LoginFormController;
import com.sample.app.controller.user.LogoutController;
import com.sample.app.controller.user.RegisterController;
import com.sample.app.controller.user.RegisterFormController;
import com.sample.app.controller.user.RegisterSuccessController;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FrontControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 6396297895804052352L;
	
	private Map<String, Controller> controllerMap = new HashMap<>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		  //INIT메소드임
		controllerMap.put("/home.hta", new HomeController());
		
		controllerMap.put("/user/registerform.hta", new RegisterFormController());
		controllerMap.put("/user/register.hta", new RegisterController());
		controllerMap.put("/user/registerSuccess.hta", new RegisterSuccessController());
		controllerMap.put("/user/loginform.hta", new LoginFormController());
		controllerMap.put("/user/login.hta", new LoginController());
		controllerMap.put("/user/logout.hta", new LogoutController());
	
		controllerMap.put("/post/list.hta", new ListController());
		controllerMap.put("/post/form.hta", new FormController());
		controllerMap.put("/post/insert.hta", new InsertController());
		controllerMap.put("/post/read.hta", new ReadController());
		controllerMap.put("/post/detail.hta", new DetailController());
		controllerMap.put("/post/modifyform.hta", new ModifyFormController());
		controllerMap.put("/post/modify.hta", new ModifyController());
		controllerMap.put("/post/delete.hta", new DeleteController());
		
		controllerMap.put("/post/insertComment.hta", new InsertCommentController());
		controllerMap.put("/post/deleteComment.hta", new DeleteCommentController());
		
		controllerMap.put("/todo/list.hta", new TodoListController());
	    controllerMap.put("/todo/form.hta", new TodoRegisterFormController());
	    controllerMap.put("/todo/register.hta", new TodoRegisterController());
	    controllerMap.put("/todo/detail.hta", new TodoDetailController());
	    controllerMap.put("/todo/modifyform.hta", new TodoModifyFormController());
	    controllerMap.put("/todo/modify.hta", new TodoModifyController());
	    controllerMap.put("/todo/addwork.hta", new TodoAddWorkController());
	  
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String requestURI = request.getRequestURI().replace(request.getContextPath(), "");
		System.out.println("### ["+requestURI+"] 요청 처리 시작");
		Controller controller = controllerMap.get(requestURI);
		if (controller == null) {
			throw new ServletException("["+requestURI+"]와 매핑되는 컨트롤러를 찾을 수 없습니다.");
		}
		
		try {
			System.out.println("### [" + controller.getClass().getSimpleName() + "]의 execute(request, response) 실행 시작");
			String path = controller.execute(request, response);
			System.out.println("### [" + controller.getClass().getSimpleName() + "]의 execute(request, response) 실행 종료");
			
			System.out.println("### [" + controller.getClass().getSimpleName() + "]의 반환값: " + path);
			if (path == null) {
				throw new Exception("[" + controller.getClass().getSimpleName() + "] 반환값이 null입니다.");
			}

			if (path.startsWith("redirect:") && path.contains(".hta")) {
				if (!path.contains(".hta")) {
					throw new Exception("[" + controller.getClass().getSimpleName()+ "] 유효한 재요청 URL이아닙니다.");					
				} else {
					response.sendRedirect(path.replace("redirect:", ""));	
					System.out.println("### 재요청 URL [" + path.replace("redirect:", "") + "]을 응답으로 보낸다.");
				}
			} else {
				if (!path.endsWith(".jsp")) {
					throw new Exception("[" + controller.getClass().getSimpleName()+ "] 유효한 JSP 이름이 아닙니다.");
				} else {
					request.getRequestDispatcher("/WEB-INF/views/" + path).forward(request, response);
					System.out.println("### [" + "/WEB-INF/views/" + path + "]로 내부이동 시킨다..");
				}
			}			
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		} finally {
			System.out.println("### ["+requestURI+"] 요청 처리 종료");
			System.out.println();
			System.out.println();
		}	
	}
}
