import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.Iterator;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

public class FileUploadServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, 
							HttpServletResponse response)
			throws ServletException, IOException {
			
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				try {
					processMultipartRequest(request, response);
				} catch (Exception exception) {
					showErrorMessage(request, response, "Îרטבךא סונגונא");
				}
			}
		} catch (Exception exception) {
			System.err.println(exception);
		}
	}
	
	private void processMultipartRequest(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {	
		ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
		Iterator<FileItem> iterator = servletFileUpload.parseRequest(request).iterator();
		
		FileItem fileToUpload = null;
		String fileName = null;
		while (iterator.hasNext()) {
			FileItem item = iterator.next();
			if (!item.isFormField()) {
				if ("file_to_upload".equals(item.getFieldName())) {
					if ("".equals(item.getName())) {
						showErrorMessage(request, response, "Âûבונטעו פאיכ");
					}
					fileToUpload = item;
				}
			}
			else {
				if ("file_name".equals(item.getFieldName())) {
					if (item.getString() == null || "".equals(item.getString())) {
						showErrorMessage(request, response, "Âûבונטעו טלÿ פאיכא");
					}
					fileName = item.getString();
				}
			}
		}
		if (fileToUpload != null && fileName != null) {
			saveImage(fileToUpload, fileName);
			response.sendRedirect("gallery.jsp");
		}
	}
	
	private void showErrorMessage(HttpServletRequest request, HttpServletResponse response, String message) 
			throws ServletException, IOException {
		request.setAttribute("errorMessage", message);
		request.getRequestDispatcher("/").forward(request, response);
	}
	
	private void saveImage(FileItem fileItem, String fileName) 
			throws Exception {
		File uploadedFile = new File(System.getenv("CATALINA_HOME") + File.separator + "webapps" 
				+ File.separator + "ROOT" + File.separator + "images", fileName);
		fileItem.write(uploadedFile);
	}
}