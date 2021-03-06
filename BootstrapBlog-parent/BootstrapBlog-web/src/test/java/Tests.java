import cn.porkchop.bootstrapblog.service.LinkService;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tests {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring/application*");
        LinkService bean = classPathXmlApplicationContext.getBean(LinkService.class);
        System.out.println(bean.findAllByOrder());
    }

    @Test
    public void insertAndUpdateIndex() throws Exception {
        // 创建HttpSolrServer,等价于下面,默认是collection1
        HttpSolrServer server = new HttpSolrServer("https://blog.porkchop.cn:443/solr/blog_collection");

        // 创建Document对象
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", "blog:1");
        doc.addField("blog_title", "centos7安装ss服务端与bbr算法");
        doc.addField("blog_summary", "1、安装ShadowSocks[root@localhost ~]# yum install python-setuptools && easy_install pip [root@localhost ~]# pip install shadowsocks2、创建配置文件/etc/shadowsocks.json[root@localhost /]# touch /etc/shadowsocks.");
        doc.addField("blog_releaseDate", new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date()));
        doc.addField("blog_content", "1、安装ShadowSocks[root@localhost ~]# yum install python-setuptools && easy_install pip [root@localhost ~]# pip install shadowsocks2、创建配置文件/etc/shadowsocks.json[root@localhost /]# touch /etc/shadowsocks.");
        doc.addField("blog_keyword", "安装 mysql");

        // 将Document对象添加到索引库
        server.add(doc);
        // 提交
        server.commit();
    }
    @Test
    public void delete() throws Exception {
        // 创建HttpSolrServer,等价于下面,默认是collection1
        HttpSolrServer server = new HttpSolrServer("https://blog.porkchop.cn:443/solr/blog_collection");
        server.deleteById("change.me");
        // 提交
        server.commit();
    }
}
