/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mavenproject10;

/**
 *
 * @author Stanislav
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

@Controller
@RequestMapping("/fileupload/upload.htm")
public class FileuploadController {

    @Autowired
    ServletContext context;

    @RequestMapping(method = RequestMethod.POST)
    ModelAndView upload(HttpServletRequest request, HttpServletResponse response) {

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        String fileName="";
        int size=0;
        ArrayList<String> result = new ArrayList<String>();
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List items = upload.parseRequest(request);
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    fileName=item.getName();
                    System.out.println("file name " + item.getName());
                    JAXBContext jc = JAXBContext.newInstance(CustomersType.class);
                    SAXParserFactory spf = SAXParserFactory.newInstance();
                    XMLReader xmlReader = spf.newSAXParser().getXMLReader();
                    InputSource inputSource = new InputSource(new InputStreamReader(item.getInputStream(), "UTF-8"));
                    SAXSource source = new SAXSource(xmlReader, inputSource);
                    Unmarshaller unmarshaller = jc.createUnmarshaller();
                    CustomersType data2 = (CustomersType) unmarshaller.unmarshal(source);
                    //System.out.println("size " + data2.getCustomer().size());
                    size=data2.getCustomer().size();
                    for (CustomerType customer : data2.getCustomer()) {
                        System.out.println(customer.toString());
                    }
                    //����� ���� �������
                    double summ = 0.0;
                    HashMap<Integer, Float> ordersMap = new HashMap<Integer, Float>();
                    for (CustomerType customer : data2.getCustomer()) {
                        for (OrderType orderType : customer.getOrders().getOrder()) {
                            Float summPerOrder = 0.0f;
                            //System.out.println(orderType);
                            for (PositionType positionType : orderType.getPositions().getPosition()) {
                                //System.out.println(positionType);
                                summPerOrder += positionType.getCount() * positionType.getPrice();
                                summ += positionType.getCount() * positionType.getPrice();
                            }
                            ordersMap.put(orderType.getId(), summPerOrder);
                        }
                    }
                    summ = new BigDecimal(summ).setScale(2, RoundingMode.UP).doubleValue();
                    System.out.println("����� ���� ������� " + summ);
                    result.add("����� ���� ������� " + summ);

                    //������ � ������������ ������ ������
                    HashMap<Integer, Float> customersMap = new HashMap<Integer, Float>();
                    for (CustomerType customer : data2.getCustomer()) {
                        Float summPerCust = 0.0f;
                        customersMap.put(customer.getId(), summPerCust);
                        for (OrderType orderType : customer.getOrders().getOrder()) {
                            for (PositionType positionType : orderType.getPositions().getPosition()) {
                                summPerCust += positionType.getCount() * positionType.getPrice();
                            }
                        }
                        //System.out.println(customer.getId() + " orders " + summPerCust);
                        customersMap.put(customer.getId(), summPerCust);
                    }
                    TreeMap sortedMap = sortByValue(customersMap);
                    System.out.println("������ " + sortedMap.keySet().toArray()[0] + " � ������������ ������ ������: " + sortedMap.get(sortedMap.firstKey()));
                    result.add("������ " + sortedMap.keySet().toArray()[0] + " � ������������ ������ ������: " + sortedMap.get(sortedMap.firstKey()));

                    //����� ������������� ������
                    TreeMap sortedMapOrders = sortByValue(ordersMap);
                    System.out.println("����� ������������� ������ " + sortedMapOrders.keySet().toArray()[0] + " �����: " + sortedMapOrders.get(sortedMapOrders.firstKey()));
                    result.add("����� ������������� ������ " + sortedMapOrders.keySet().toArray()[0] + " �����: " + sortedMapOrders.get(sortedMapOrders.firstKey()));

                    //����� ������������ ������
                    System.out.println("����� ������������� ������ " + sortedMapOrders.keySet().toArray()[sortedMapOrders.keySet().toArray().length - 1] + " �����: " + sortedMapOrders.get(sortedMapOrders.lastKey()));
                    result.add("����� ������������� ������ " + sortedMapOrders.keySet().toArray()[sortedMapOrders.keySet().toArray().length - 1] + " �����: " + sortedMapOrders.get(sortedMapOrders.lastKey()));
                    
                    //���������� �������
                    System.out.println("���������� ������� " + sortedMapOrders.size());
                    result.add("���������� ������� " + sortedMapOrders.size());
                    
                    
                    //������� ����� �������
                    ArrayList<Float> floats = new ArrayList<Float>(sortedMapOrders.values());
                    Float summAvg = 0.0f;
                    Float avg = 0.0f;
                    for(Float f: floats){
                        summAvg+=f;
                    }
                    avg = new BigDecimal(summAvg/floats.size()).setScale(2, RoundingMode.UP).floatValue();
                    System.out.println("������� ����� ������� " +avg);
                    result.add("������� ����� ������� " +avg);
                    

                }
            } catch (FileUploadException e) {
                System.out.println("FileUploadException:- " + e.getMessage());
            } catch (JAXBException ex) {
                //Logger.getLogger(FileuploadController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(FileuploadController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileuploadController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(FileuploadController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(FileuploadController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        ModelAndView modelAndView = new ModelAndView("fileuploadsuccess");
        modelAndView.addObject("files", result);
        modelAndView.addObject("name", fileName);
        modelAndView.addObject("size", size);
        return modelAndView;

    }

    public TreeMap sortByValue(Map unsortedMap) {
        TreeMap sortedMap = new TreeMap(new ValueComparator(unsortedMap));
        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }

    class ValueComparator implements Comparator {

        Map map;

        public ValueComparator(Map map) {
            this.map = map;
        }

        public int compare(Object keyA, Object keyB) {
            Comparable valueA = (Comparable) map.get(keyA);
            Comparable valueB = (Comparable) map.get(keyB);
            return valueB.compareTo(valueA);
        }
    }
}
