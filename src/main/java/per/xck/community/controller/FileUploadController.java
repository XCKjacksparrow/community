package per.xck.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import per.xck.community.dto.FileDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class FileUploadController {


    @RequestMapping("/img/upload")
    @ResponseBody
    public FileDTO upload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file,
                          HttpServletRequest request){

        String trueFileName = file.getOriginalFilename();

        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));

        String fileName = System.currentTimeMillis()+"_" + ((int)Math.random()*100+900) + suffix;

        String path = System.getProperty("user.dir");
        path = path + ("/src/main/resources/static/uploadImgs/");
        System.out.println(path);

        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }


        FileDTO fileDTO = new FileDTO();
        fileDTO.setUrl("/uploadImgs/" + fileName);
        fileDTO.setMessage("success");
        fileDTO.setSuccess(1);
        return fileDTO;
    }

}