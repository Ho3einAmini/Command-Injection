package ir.isc.sqli.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;


@RestController("API")
public class API {

    @GetMapping("/")
    private ModelAndView index()
    {
        return new ModelAndView("index");
    }

    @GetMapping("/blindPing")
    private ModelAndView blind()
    {
        return new ModelAndView("blind");
    }


    @GetMapping("/run")
    private String run(HttpServletRequest request)
    {
        try {
            String server = request.getParameter("server");
            System.out.println("Request for " + server);
            Process process = Runtime.getRuntime().exec("cmd.exe /c ping -n 1 " +  server);
            process.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String result = "";
            String line = "";

            while ((line = b.readLine()) != null) {
                result += line + "<br/>";
            }
            b.close();
            System.out.println(result);
            return result;
        } catch (Exception e) {
            return "Not Found";
        }
    }
    @GetMapping("/blind")
    private String blind(HttpServletRequest request)
    {
        try {
            String server = request.getParameter("server");
            System.out.println("Request for " + server);
            Process process = Runtime.getRuntime().exec("cmd.exe /c ping -n 1 " +  server);
            process.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String result = "";
            String line = "";

            while ((line = b.readLine()) != null) {
                result += line + "<br/>";
            }
            b.close();
            System.out.println(result);
            if (result.contains("Reply from"))
                return "Resolved";
            return "Host not found!!";
        } catch (Exception e) {
            return "Error";
        }
    }

}
