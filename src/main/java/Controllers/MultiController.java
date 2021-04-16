/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Michail Sitmalidis
 */
@Controller
public class MultiController {

    @RequestMapping(value = "/malti", method = RequestMethod.GET)
    public String Ordered(ModelMap model) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(formatter.format(date));
        String startTime = formatter.format(date);

        Date date1 = new Date();
        System.out.println(formatter.format(date1));
        String endTime = formatter.format(date1);

        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);

        String workDir = loadFile();
        model.addAttribute("data", workDir);
        return "multi";

    }

    public String loadFile() {

        String internalPath = this.getClass().getName().replace(".", File.separator);
        String externalPath = System.getProperty("user.dir") + File.separator + "src";
        String workDir = externalPath + File.separator + internalPath.substring(0, internalPath.lastIndexOf(File.separator));
        System.out.println(workDir);
        return workDir;
    }

    public String loadExcel() {
        String data = "";
        try {

            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println(data);

            }
            myReader.close();
            return data;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }
}
