import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.teamidea.test.ValCurs;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ValCursTest {
    @Test
    public void nodeListLengthShouldBeAboveZero() throws IOException, ParserConfigurationException, SAXException {
        NodeList nodeList = ValCurs.connectAndParseXML(ValCurs.XML_URL);
        Assert.assertTrue(nodeList.getLength()>0);
    }
}
