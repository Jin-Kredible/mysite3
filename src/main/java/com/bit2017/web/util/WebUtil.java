package com.bit2017.web.util;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class WebUtil {
	public static void forward(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
	
	public static void sendRedirect(String url, HttpServletRequest request, HttpServletResponse response ) throws IOException{
		response.sendRedirect(url);
	}
	
	public static String checkNullParam(String s, String value) {
		return s != null ? s : value;
	}

	public static int checkNullParam(String s, int value) {
		return s != null ? checkIntParam(s, value) : value;
	}

	public static long checkNullParam(String s, long value) {
		return s != null ? checkLongParam(s, value) : value;
	}

	public static int checkIntParam(String s, int value) {
		return (s != null && s.matches("\\d*\\.?\\d+")) ? Integer.parseInt(s) : value;
	}

	public static long checkLongParam(String s, long value) {
		return (s != null && s.matches("\\d*\\.?\\d+")) ? Long.parseLong(s) : value;
	}	
	
}
