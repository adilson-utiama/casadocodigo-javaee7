package br.com.casadocodigo.servlets;

import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.casadocodigo.infra.FileSaver;

@WebServlet("/file/*")
public class FileServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		//pega toda a URl, divide e pega so o que vem depois de /file
		String path = req.getRequestURI().split("/file")[1];

		//concatenando caminho do filesystem
	    Path source = Paths.get(FileSaver.SERVER_PATH + "/" + path);
	    
	    //O FileNameMap pode ser obtido usando a classe URLConnection chamando o método estático da classe getFileNameMap
	    FileNameMap fileNameMap = URLConnection.getFileNameMap();
	    
	    //O Path servirá como fonte para o FileNameMap conseguir chegar no arquivo e obter o contentType
	    String contentType = fileNameMap.getContentTypeFor("file:"+source);

	    //resetando o response
	    res.reset();
	    
	    //setando o contenttype
	    res.setContentType(contentType);
	    res.setHeader("Content-Length", String.valueOf(Files.size(source)));
	    res.setHeader("Content-Disposition", 
	            "filename=\""+source.getFileName().toString() + "\"");
	    
	    //transferindo o arquivo do servidor para o response
	    FileSaver.transfer(source, res.getOutputStream());
	}
}
