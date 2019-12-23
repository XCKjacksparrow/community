package per.xck.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.xck.community.dto.FileDTO;

@Controller
public class FileController {


    /*
        {
            success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
            message : "提示的信息，上传成功或上传失败及错误信息等。",
            url     : "图片地址"        // 上传成功时才返回
        }
     */
    @RequestMapping("/img/upload")
    @ResponseBody
    public FileDTO upload(){
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setMessage("上传成功");
        fileDTO.setUrl("/dist/images/logos/editormd-logo-16x16.png");
        return fileDTO;
    }
}
