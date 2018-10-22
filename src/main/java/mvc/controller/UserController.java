package mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.model.*;

@Controller
public class UserController {

	@RequestMapping("/")
	public String execute() {
		return "signIn";

	}

	@RequestMapping("/signUp")
	public String form() {
		return "signUp";
	}

	@RequestMapping("/addUser")
	public void adiciona(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao = new DAO();
		 User user = new User();
		 user.setName(request.getParameter("name"));
		 user.setUsername((request.getParameter("username")));
		 user.setPassword(request.getParameter("password"));

		 dao.adicionaUser(user);
		 PrintWriter out = response.getWriter();
		 out.println("<html><body>");
		 out.println("adicionado" + user.getIdUser());
		 out.println("</body></html>");
		 dao.close();
	}
	@RequestMapping("/checkUser")
	public String checkUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao = new DAO();
		String site = "signUpError";
		boolean userExists = false;
		User user = new User();
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		
		userExists = dao.checkUser(username);
		System.out.println("Estou dps do userExists");
		
		if(!userExists) {
			dao.adicionaUser(user);
			site = "signIn";
		}
		return site;

		
	}
	@RequestMapping("/verifyUser")
	public String verifyUser(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao = new DAO();
		String site = "signInError";
		boolean verifyUser = false;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String isErrorHidden = "hidden";
		String isPassCheckHidden = "hidden";
		
		HttpSession session = request.getSession();
		session.setAttribute("isPassCheckHidden", isPassCheckHidden);
		session.setAttribute("isErrorHidden", isErrorHidden);
		
//		model.addAttribute("isPassCheckHidden", isPassCheckHidden);
//		model.addAttribute("isErrorHidden", isErrorHidden);
//

		verifyUser = dao.verifyUser(username, password);
			
		
		if(verifyUser) {
			site = "index";
			Integer idUser = dao.getIdFromUsername(username);
			session.setAttribute("idUser", idUser);
		}
//		request.getRequestDispatcher("index.jsp").include(request, response);

		return site;
	}
	@RequestMapping("/removeUser")
	public void removeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 DAO dao = new DAO();
		 dao.removeUser(Integer.valueOf(request.getParameter("idUser")));
		  PrintWriter out = response.getWriter();
		  out.println("<html><body>");
		  out.println("removido");
		  out.println("</body></html>");
		  dao.close();
	}
	@RequestMapping("/editUser")
	public void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		DAO dao = new DAO();
		
		User user = new User();
//		note.setId(Integer.valueOf(request.getParameter("id")));
		user.setName(request.getParameter("name"));
		user.setUsername((request.getParameter("username")));
		user.setPassword((request.getParameter("password")));
		user.setIdUser(Integer.valueOf(request.getParameter("idUser")));

		dao.alteraUser(user);
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("atualizado" + user.getIdUser());
		out.println("</body></html>");
		dao.close();
	}
}
