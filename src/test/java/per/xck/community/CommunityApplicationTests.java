package per.xck.community;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class CommunityApplicationTests {

    String secretId = "AKIDRedttd0AG7g27vcloa6Oq42kBNKi9mSs";
    String secretKey = "IJjvqb6UdW8oENFqypIWTCeqm4wYuElV";
    COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
    // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。

    Region region = new Region("ap-nanjing");
    ClientConfig clientConfig = new ClientConfig(region);
    // 3 生成 cos 客户端。
    COSClient cosClient = new COSClient(cred, clientConfig);
    String bucket = "community-1257843792"; //存储桶名称，格式：BucketName-APPID
    CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);

    @Test
    void contextLoads() {
//        try{
//            Bucket bucketResult = cosClient.createBucket(createBucketRequest);
//        } catch (CosServiceException serverException) {
//            serverException.printStackTrace();
//        } catch (CosClientException clientException) {
//            clientException.printStackTrace();
//        }

        try {
            // 指定要上传的文件
            File localFile = new File("/dist/images/loading.gif");
            // 指定要上传到的存储桶
            String bucketName = "community-1257843792";
            // 指定要上传到 COS 上对象键
            String key = "exampleobject";
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        } catch (CosServiceException serverException) {
            serverException.printStackTrace();
        } catch (CosClientException clientException) {
            clientException.printStackTrace();
        }

    }

}
