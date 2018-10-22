package mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import mvc.model.*;


@Controller
public class NoteController {


	@RequestMapping("/addNote")
	public void addNote(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Integer idUser = (Integer) session.getAttribute("idUser");

		String jsonObj = request.getParameter("note");

		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		Map<String, String> myMap = gson.fromJson(jsonObj, type);
//		    System.out.println(myMap.values());

		System.out.println(myMap.get("noteText"));

		DAO dao = new DAO();
		Note note = new Note();
		note.setNoteText(myMap.get("noteText"));
		note.setIcon((("icon")));
		note.setColor(("#ffec9d"));
		note.setTag(("#tag"));
		note.setTitle("Title");

		note.setIdUser(idUser);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm MM/dd");
		LocalDateTime now = LocalDateTime.now();
		note.setTime(dtf.format(now));

//			note.setId(Integer.valueOf(request.getParameter("id")));

		dao.adicionaNota(note);
		note.setIdNote(dao.getLastInsertedId());
		dao.close();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println(gson.toJson(note));
		out.print(gson.toJson(note));
		out.flush();
	}
	@RequestMapping("/editNoteColor")
	public void editNoteColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		Gson gson = new Gson();
		HttpSession session = request.getSession();
		Integer idUser = (Integer)session.getAttribute("idUser");

	    System.out.println(request.getParameter("note"));
        String jsonObj = request.getParameter("note");
        
        
        Gson gson = new Gson(); 
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> myMap = gson.fromJson(jsonObj, type);
//	    System.out.println(myMap.values());
	    
	    
	    System.out.println(myMap.get("idNote"));

		DAO dao = new DAO();
		Note note = dao.getNote(Integer.parseInt(myMap.get("idNote")), idUser);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm MM/dd");
		LocalDateTime now = LocalDateTime.now();
		note.setTime(dtf.format(now));

//		note.setId(Integer.valueOf(request.getParameter("id")));
		note.setColor(myMap.get("color"));

		dao.alteraNota(note);

		dao.close();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println(gson.toJson(note));
		out.print(gson.toJson(note));
		out.flush();
	}
	@RequestMapping("/editNoteText")
	public void editNoteText(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		Gson gson = new Gson();

	    System.out.println(request.getParameter("note"));
        String jsonObj = request.getParameter("note");
        
    	HttpSession session = request.getSession();
		Integer idUser = (Integer)session.getAttribute("idUser");
        
        
        Gson gson = new Gson(); 
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> myMap = gson.fromJson(jsonObj, type);
	    
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm MM/dd");
		LocalDateTime now = LocalDateTime.now();
		
		
	    System.out.println(myMap.get("idNote"));

		DAO dao = new DAO();
		Note note = dao.getNote(Integer.parseInt(myMap.get("idNote")), idUser);
		note.setTime(dtf.format(now));
//		note.setId(Integer.valueOf(request.getParameter("id")));
		note.setNoteText(myMap.get("noteText"));
		dao.alteraNota(note);
       

		dao.close();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println(gson.toJson(note));
		out.print(gson.toJson(note));
		out.flush();
	}
	@RequestMapping("/editNoteTag")
	public void editNoteTag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		Gson gson = new Gson();

//	    System.out.println(request.getParameter("note"));
        String jsonObj = request.getParameter("note");
        
		HttpSession session = request.getSession();
		Integer idUser = (Integer)session.getAttribute("idUser");
        
        Gson gson = new Gson(); 
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> myMap = gson.fromJson(jsonObj, type);
//	    System.out.println(myMap.values());
	    
	    
	    System.out.println(myMap.get("tag"));

		DAO dao = new DAO();
		Note note = dao.getNote(Integer.parseInt(myMap.get("idNote")), idUser);
//		note.setId(Integer.valueOf(request.getParameter("id")));
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm MM/dd");
		LocalDateTime now = LocalDateTime.now();
		note.setTime(dtf.format(now));

		note.setTag(myMap.get("tag"));

		dao.alteraNota(note);

		dao.close();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println(gson.toJson(note));
		out.print(gson.toJson(note));
		out.flush();
	}
	@RequestMapping("/editNoteTitle")
	public void editNoteTitle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String jsonObj = request.getParameter("note");
        
		HttpSession session = request.getSession();
		Integer idUser = (Integer)session.getAttribute("idUser");
        
        Gson gson = new Gson(); 
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> myMap = gson.fromJson(jsonObj, type);
//	    System.out.println(myMap.values());
	    
	    
	    System.out.println(myMap.get("title"));

		DAO dao = new DAO();
		Note note = dao.getNote(Integer.parseInt(myMap.get("idNote")), idUser);
//		note.setId(Integer.valueOf(request.getParameter("id")));
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm MM/dd");
		LocalDateTime now = LocalDateTime.now();
		note.setTime(dtf.format(now));

		note.setTitle(myMap.get("title"));

		dao.alteraNota(note);

		dao.close();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println(gson.toJson(note));
		out.print(gson.toJson(note));
		out.flush();
	}
	@RequestMapping("/removeNote")
	public void removeNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		Gson gson = new Gson();
	    System.out.println(request.getParameter("note"));
        String jsonObj = request.getParameter("note");
        
        
        Gson gson = new Gson(); 
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> myMap = gson.fromJson(jsonObj, type);
//	    System.out.println(myMap.values());
	    
	    
	    System.out.println(myMap.get("idNote"));

		DAO dao = new DAO();

		dao.removeNota(Integer.parseInt(myMap.get("idNote")));


		dao.close();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println(gson.toJson(myMap));
		out.print(gson.toJson(myMap));
		out.flush();
}
	@RequestMapping("/searchNote")
	public void searchNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String jsonObj = request.getParameter("query");
        
		HttpSession session = request.getSession();
		Integer idUser = (Integer)session.getAttribute("idUser");
        
        Gson gson = new Gson(); 
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> myMap = gson.fromJson(jsonObj, type);
//	    System.out.println(myMap.values());
	    

	    System.out.println(myMap.get("query"));

		DAO dao = new DAO();
		List<Note> notes = dao.searchNotesByText(myMap.get("query"),idUser);
		
		dao.close();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println(gson.toJson(notes));
		out.print(gson.toJson(notes));
		out.flush();
	}
}
