import com.ace.trade.entity.TradeUser;
import com.ace.trade.mapper.TradeUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <B>描述: </B><br/>
 * <B>作者: </B>carl.yu<br/>
 * <B>时间: </B>2017/2/21<br/>
 * <B>版本: </B>v1.0.0 <br/>
 * <B>历史: </B> (版本 作者 时间 注释) <br/>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:xml/spring-dao.xml")
public class TestDao {

    @Autowired
    private TradeUserMapper tradeUserMapper;

    @Test
    public void test() throws Exception {
        TradeUser tu = new TradeUser();
        tu.setUserName("张三");
        tu.setUserPassword("111111");
        int i = tradeUserMapper.insert(tu);
        System.out.println("影响行数:" + i);
        System.out.println(tu.getUserId());
    }
}
