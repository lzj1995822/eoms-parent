package com.eoms.snmp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.eoms.domain.dto.SystemDTO;
import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.MessageProcessingModel;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.Null;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.PDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

/**
 * 封装Snmp4j
 * @author asher
 */
public class SnmpUtils {

    public static final int DEFAULT_VERSION = SnmpConstants.version2c; //默认采用的snmp协议版本
    public static final String DEFAULT_PROTOCOL = "udp";  //默认采用的udp传输协议
    public static final int DEFAULT_PORT = 161;  //默认snmp服务端口
    public static final long DEFAULT_TIMEOUT = 3 * 1000L; //超时设置
    public static final int DEFAULT_RETRY = 3;  //重试次数

    /*创建CommunityTarget对象 社区为public
     *@return CommunityTarget
     */
    public static CommunityTarget createDefault(String ip) {
        return  createDefault(ip,"public");
    }
    /**
     * 创建对象communityTarge
     * @return CommunityTarget
     */
    public static CommunityTarget createDefault(String ip, String community) {
        Address address = GenericAddress.parse(DEFAULT_PROTOCOL + ":" + ip
                + "/" + DEFAULT_PORT);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(community));
        target.setAddress(address);
        target.setVersion(DEFAULT_VERSION);
        target.setTimeout(DEFAULT_TIMEOUT); // milliseconds
        target.setRetries(DEFAULT_RETRY);
        return target;
    }
    /*获取信息*/
    public static PDU snmpGet(String ip, String community, String oid) {

        CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        PDU response = null;
        try {
            PDU pdu = new PDU();
            // pdu.add(new VariableBinding(new OID(new int[]
            // {1,3,6,1,2,1,1,2})));
            pdu.add(new VariableBinding(new OID(oid)));

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            System.out.println("-------> 发送PDU <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, target);
            System.out.println("PeerAddress:" + respEvent.getPeerAddress());
            response = respEvent.getResponse();

            if (response == null) {
                System.out.println("response is null, request time out");
            } else {

                // Vector<VariableBinding> vbVect =
                // response.getVariableBindings();
                // System.out.println("vb size:" + vbVect.size());
                // if (vbVect.size() == 0) {
                // System.out.println("response vb size is 0 ");
                // } else {
                // VariableBinding vb = vbVect.firstElement();
                // System.out.println(vb.getOid() + " = " + vb.getVariable());
                // }

                System.out.println("response pdu size is " + response.size());
                for (int i = 0; i < response.size(); i++) {
                    VariableBinding vb = response.get(i);
                    System.out.println(vb.getOid() + " = " + vb.getVariable());
                }

            }
            System.out.println("SNMP GET one OID value finished !");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SNMP Get Exception:" + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }
            return response;
        }
    }
    /*获取列表信息，一次获取多条信息*/
    public static PDU snmpGetListByOid(String ip, String community, List<OID> oidList)
    {
        CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        PDU response = null;
        try {
            PDU pdu = new PDU();

            for(OID oid:oidList)
            {
                pdu.add(new VariableBinding(oid));
            }

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            System.out.println("-------> 发送PDU <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, target);
            System.out.println("PeerAddress:" + respEvent.getPeerAddress());
            response = respEvent.getResponse();

            if (response == null) {
                System.out.println("response is null, request time out");
            } else {

                System.out.println("response pdu size is " + response.size());
                for (int i = 0; i < response.size(); i++) {
                    VariableBinding vb = response.get(i);
                    System.out.println(vb.getOid() + " = " + vb.getVariable());
                }
            }
            System.out.println("SNMP GET one OID value finished !");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SNMP Get Exception:" + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }
            return response;
        }
    }
    /*获取列表信息，一次获取多条信息*/
    public static PDU snmpGetList(String ip, String community, List<String> oidList)
    {
        CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        PDU  response = null;
        try {
            PDU pdu = new PDU();

            for(String oid:oidList)
            {
                pdu.add(new VariableBinding(new OID(oid)));
            }

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            System.out.println("-------> 发送PDU <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, target);
            System.out.println("PeerAddress:" + respEvent.getPeerAddress());
            response = respEvent.getResponse();

            if (response == null) {
                System.out.println("response is null, request time out");
            } else {

                System.out.println("response pdu size is " + response.size());
                for (int i = 0; i < response.size(); i++) {
                    VariableBinding vb = response.get(i);
                    System.out.println(vb.getOid() + " = " + vb.getVariable());
                }

            }
            System.out.println("SNMP GET one OID value finished !");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SNMP Get Exception:" + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }
            return response;
        }
    }
    /*异步获取信息列表*/
    public static void snmpAsynGetList(String ip, String community,List<String> oidList)
    {
        CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        try {
            PDU pdu = new PDU();

            for(String oid:oidList)
            {
                pdu.add(new VariableBinding(new OID(oid)));
            }

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            System.out.println("-------> 发送PDU <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, target);
            System.out.println("PeerAddress:" + respEvent.getPeerAddress());
            PDU response = respEvent.getResponse();

            /*异步获取*/
            final CountDownLatch latch = new CountDownLatch(1);
            ResponseListener listener = new ResponseListener() {
                public void onResponse(ResponseEvent event) {
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);
                    PDU response = event.getResponse();
                    PDU request = event.getRequest();
                    System.out.println("[request]:" + request);
                    if (response == null) {
                        System.out.println("[ERROR]: response is null");
                    } else if (response.getErrorStatus() != 0) {
                        System.out.println("[ERROR]: response status" + response.getErrorStatus() + " Text:"+ response.getErrorStatusText());
                    } else {
                        System.out.println("Received response Success!");
                        for (int i = 0; i < response.size(); i++) {
                            VariableBinding vb = response.get(i);
                            System.out.println(vb.getOid() + " = " + vb.getVariable());
                        }
                        System.out.println("SNMP Asyn GetList OID finished. ");
                        latch.countDown();
                    }
                }
            };

            pdu.setType(PDU.GET);
            snmp.send(pdu, target, null, listener);
            System.out.println("asyn send pdu wait for response...");

            boolean wait = latch.await(30, TimeUnit.SECONDS);
            System.out.println("latch.await =:" + wait);

            snmp.close();

            System.out.println("SNMP GET one OID value finished !");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SNMP Get Exception:" + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }

        }
    }
    /*获取表格*/
    public static List<PDU> snmpWalk(String ip, String community, String targetOid)
    {
        CommunityTarget target = createDefault(ip, community);
        TransportMapping transport = null;
        Snmp snmp = null;
        PDU response = null;
        List<PDU> pduList = new ArrayList<>();
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            transport.listen();

            PDU pdu = new PDU();
            OID targetOID = new OID(targetOid);
            pdu.add(new VariableBinding(targetOID));

            boolean finished = false;
            System.out.println("----> demo start <----");
            while (!finished) {
                VariableBinding vb = null;
                ResponseEvent respEvent = snmp.getNext(pdu, target);

                response = respEvent.getResponse();
                pduList.add(response);
                if (null == response) {
                    System.out.println("responsePDU == null");
                    finished = true;
                    break;
                } else {
                    vb = response.get(0);
                }
                // check finish
                finished = checkWalkFinished(targetOID, pdu, vb);
                if (!finished) {
                    System.out.println("==== walk each vlaue :");
                    System.out.println(vb.getOid() + " = " + vb.getVariable());

                    // Set up the variable binding for the next entry.
                    pdu.setRequestID(new Integer32(0));
                    pdu.set(0, vb);
                } else {
                    System.out.println("SNMP walk OID has finished.");
                    snmp.close();
                }
            }
            System.out.println("----> demo end <----");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SNMP walk Exception: " + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }
            return pduList;
        }
    }

    private static boolean checkWalkFinished(OID targetOID, PDU pdu,
                                             VariableBinding vb) {
        boolean finished = false;
        if (pdu.getErrorStatus() != 0) {
            System.out.println("[true] responsePDU.getErrorStatus() != 0 ");
            System.out.println(pdu.getErrorStatusText());
            finished = true;
        } else if (vb.getOid() == null) {
            System.out.println("[true] vb.getOid() == null");
            finished = true;
        } else if (vb.getOid().size() < targetOID.size()) {
            System.out.println("[true] vb.getOid().size() < targetOID.size()");
            finished = true;
        } else if (targetOID.leftMostCompare(targetOID.size(), vb.getOid()) != 0) {
            System.out.println("[true] targetOID.leftMostCompare() != 0");
            finished = true;
        } else if (Null.isExceptionSyntax(vb.getVariable().getSyntax())) {
            System.out.println("[true] Null.isExceptionSyntax(vb.getVariable().getSyntax())");
            finished = true;
        } else if (vb.getOid().compareTo(targetOID) <= 0) {
            System.out.println("[true] Variable received is not "+ "lexicographic successor of requested " + "one:");
            System.out.println(vb.toString() + " <= " + targetOID);
            finished = true;
        }
        return finished;

    }
    /*异步获取表格*/
    public static void snmpAsynWalk(String ip, String community, String oid)
    {
        final CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        try {
            System.out.println("----> demo start <----");

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();

            final PDU pdu = new PDU();
            final OID targetOID = new OID(oid);
            final CountDownLatch latch = new CountDownLatch(1);
            pdu.add(new VariableBinding(targetOID));

            ResponseListener listener = new ResponseListener() {
                public void onResponse(ResponseEvent event) {
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);

                    try {
                        PDU response = event.getResponse();
                        // PDU request = event.getRequest();
                        // System.out.println("[request]:" + request);
                        if (response == null) {
                            System.out.println("[ERROR]: response is null");
                        } else if (response.getErrorStatus() != 0) {
                            System.out.println("[ERROR]: response status"+ response.getErrorStatus() + " Text:"+ response.getErrorStatusText());
                        } else {
                            System.out.println("Received Walk response value :");
                            VariableBinding vb = response.get(0);

                            boolean finished = checkWalkFinished(targetOID,
                                    pdu, vb);
                            if (!finished) {
                                System.out.println(vb.getOid() + " = "+ vb.getVariable());
                                pdu.setRequestID(new Integer32(0));
                                pdu.set(0, vb);
                                ((Snmp) event.getSource()).getNext(pdu, target,
                                        null, this);
                            } else {
                                System.out.println("SNMP Asyn walk OID value success !");
                                latch.countDown();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        latch.countDown();
                    }

                }
            };

            snmp.getNext(pdu, target, null, listener);
            System.out.println("pdu 已发送,等到异步处理结果...");

            boolean wait = latch.await(30, TimeUnit.SECONDS);
            System.out.println("latch.await =:" + wait);
            snmp.close();

            System.out.println("----> demo end <----");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SNMP Asyn Walk Exception:" + e);
        }
    }
    /*设置信息*/
    public static void setPDU(String ip,String community,String oid) throws IOException
    {
        CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(oid),new OctetString("shangrao")));
        pdu.setType(PDU.SET);

        DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        snmp.listen();
        System.out.println("-------> 发送PDU <-------");
        snmp.send(pdu, target);
        snmp.close();
    }

    //获取cpu使用率
    public static int collectCPU(String ip) {
        TransportMapping transport = null ;
        Snmp snmp = null ;
        CommunityTarget target;
        int cpuUzi = 0;
        String[] oids = {"1.3.6.1.2.1.25.3.3.1.2"};
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);//创建snmp
            snmp.listen();//监听消息
            target = new CommunityTarget();
            target.setCommunity(new OctetString("public"));
            target.setRetries(2);
            target.setAddress(GenericAddress.parse("udp:"+ip+"/161"));
            target.setTimeout(8000);
            target.setVersion(SnmpConstants.version2c);
            TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
                @Override
                public PDU createPDU(Target arg0) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }

                @Override
                public PDU createPDU(MessageProcessingModel messageProcessingModel) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }
            });
            OID[] columns = new OID[oids.length];
            for (int i = 0; i < oids.length; i++)
                columns[i] = new OID(oids[i]);
            List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
            if(list.size()==1 && list.get(0).getColumns()==null){
                System.out.println(" null");
            }else{
                int percentage = 0;
                for(TableEvent event : list){
                    VariableBinding[] values = event.getColumns();
                    if(values != null)
                        percentage += Integer.parseInt(values[0].getVariable().toString());
                }
                cpuUzi = percentage/list.size();
                System.out.println("CPU利用率为："+percentage/list.size()+"%");
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(transport!=null)
                    transport.close();
                if(snmp!=null)
                    snmp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return cpuUzi;
        }
    }

    //获取内存相关信息
    public static SystemDTO collectMemory(String ip) {
        TransportMapping transport = null ;
        Snmp snmp = null ;
        CommunityTarget target;
        String[] oids = {"1.3.6.1.2.1.25.2.3.1.2",  //type 存储单元类型
                "1.3.6.1.2.1.25.2.3.1.3",  //descr
                "1.3.6.1.2.1.25.2.3.1.4",  //unit 存储单元大小
                "1.3.6.1.2.1.25.2.3.1.5",  //size 总存储单元数
                "1.3.6.1.2.1.25.2.3.1.6"}; //used 使用存储单元数;
        String PHYSICAL_MEMORY_OID = "1.3.6.1.2.1.25.2.1.2";//物理存储
        String VIRTUAL_MEMORY_OID = "1.3.6.1.2.1.25.2.1.3"; //虚拟存储
        SystemDTO systemDTO = new SystemDTO();
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);//创建snmp
            snmp.listen();//监听消息
            target = new CommunityTarget();
            target.setCommunity(new OctetString("public"));
            target.setRetries(2);
            target.setAddress(GenericAddress.parse("udp:"+ip+"/161"));
            target.setTimeout(8000);
            target.setVersion(SnmpConstants.version2c);
            TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
                @Override
                public PDU createPDU(Target arg0) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }

                @Override
                public PDU createPDU(MessageProcessingModel messageProcessingModel) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }
            });
            OID[] columns = new OID[oids.length];
            for (int i = 0; i < oids.length; i++)
                columns[i] = new OID(oids[i]);
            @SuppressWarnings("unchecked")
            List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
            if(list.size()==1 && list.get(0).getColumns()==null){
                System.out.println(" null");
            }else{
                for(TableEvent event : list){
                    VariableBinding[] values = event.getColumns();
                    if(values == null) continue;
                    int unit = Integer.parseInt(values[2].getVariable().toString());//unit 存储单元大小
                    int totalSize = Integer.parseInt(values[3].getVariable().toString());//size 总存储单元数
                    int usedSize = Integer.parseInt(values[4].getVariable().toString());//used  使用存储单元数
                    String oid = values[0].getVariable().toString();
                    if (PHYSICAL_MEMORY_OID.equals(oid)){
                        systemDTO.setPhyMem((long)totalSize * unit/(1024*1024*1024));
                        systemDTO.setPhyUzi((long)usedSize*100/totalSize);
                        System.out.println("PHYSICAL_MEMORY----->物理内存大小："+(long)totalSize * unit/(1024*1024*1024)+"G   内存使用率为："+(long)usedSize*100/totalSize+"%");
                    }else if (VIRTUAL_MEMORY_OID.equals(oid)) {
                        systemDTO.setVirtualMem((long)totalSize * unit/(1024*1024*1024));
                        systemDTO.setVirtualUzi((long)usedSize*100/totalSize);
                        System.out.println("VIRTUAL_MEMORY----->虚拟内存大小："+(long)totalSize * unit/(1024*1024*1024)+"G   内存使用率为："+(long)usedSize*100/totalSize+"%");
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(transport!=null)
                    transport.close();
                if(snmp!=null)
                    snmp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return systemDTO;
        }
    }

    //获取磁盘相关信息
    public static long collectDisk(String ip) {
        TransportMapping transport = null ;
        Snmp snmp = null ;
        CommunityTarget target;
        String DISK_OID = "1.3.6.1.2.1.25.2.1.4";
        String[] oids = {"1.3.6.1.2.1.25.2.3.1.2",  //type 存储单元类型
                "1.3.6.1.2.1.25.2.3.1.3",  //descr
                "1.3.6.1.2.1.25.2.3.1.4",  //unit 存储单元大小
                "1.3.6.1.2.1.25.2.3.1.5",  //size 总存储单元数
                "1.3.6.1.2.1.25.2.3.1.6"}; //used 使用存储单元数;
        long diskUzi = 0;
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);//创建snmp
            snmp.listen();//监听消息
            target = new CommunityTarget();
            target.setCommunity(new OctetString("public"));
            target.setRetries(2);
            target.setAddress(GenericAddress.parse("udp:"+ip+"/161"));
            target.setTimeout(8000);
            target.setVersion(SnmpConstants.version2c);
            TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
                @Override
                public PDU createPDU(Target arg0) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }

                @Override
                public PDU createPDU(MessageProcessingModel messageProcessingModel) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }
            });
            OID[] columns = new OID[oids.length];
            for (int i = 0; i < oids.length; i++)
                columns[i] = new OID(oids[i]);
            @SuppressWarnings("unchecked")
            List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
            if(list.size()==1 && list.get(0).getColumns()==null){
                System.out.println(" null");
            }else{
                for(TableEvent event : list){
                    VariableBinding[] values = event.getColumns();
                    if(values == null ||!DISK_OID.equals(values[0].getVariable().toString()))
                        continue;
                    int unit = Integer.parseInt(values[2].getVariable().toString());//unit 存储单元大小
                    int totalSize = Integer.parseInt(values[3].getVariable().toString());//size 总存储单元数
                    int usedSize = Integer.parseInt(values[4].getVariable().toString());//used  使用存储单元数
                    diskUzi = (long)usedSize*100/totalSize;
                    System.out.println(getChinese(values[1].getVariable().toString())+"   磁盘大小："+(long)totalSize*unit/(1024*1024*1024)+"G   磁盘使用率为："+(long)usedSize*100/totalSize+"%");
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(transport!=null)
                    transport.close();
                if(snmp!=null)
                    snmp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return diskUzi;
        }
    }

    //服务器进程集合信息
    public static void collectProcess() {
        TransportMapping transport = null ;
        Snmp snmp = null ;
        CommunityTarget target;
        String[] oids =
                {"1.3.6.1.2.1.25.4.2.1.1",  //index
                        "1.3.6.1.2.1.25.4.2.1.2",  //name
                        "1.3.6.1.2.1.25.4.2.1.4",  //run path
                        "1.3.6.1.2.1.25.4.2.1.6",  //type
                        "1.3.6.1.2.1.25.5.1.1.1",  //cpu
                        "1.3.6.1.2.1.25.5.1.1.2"}; //memory
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            target = new CommunityTarget();
            target.setCommunity(new OctetString("public"));
            target.setRetries(2);
            target.setAddress(GenericAddress.parse("udp:127.0.0.1/161"));
            target.setTimeout(8000);
            target.setVersion(SnmpConstants.version2c);
            TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
                @Override
                public PDU createPDU(Target arg0) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }

                @Override
                public PDU createPDU(MessageProcessingModel messageProcessingModel) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }
            });
            OID[] columns = new OID[oids.length];
            for (int i = 0; i < oids.length; i++)
                columns[i] = new OID(oids[i]);
            @SuppressWarnings("unchecked")
            List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
            if(list.size()==1 && list.get(0).getColumns()==null){
                System.out.println(" null");
            }else{
                for(TableEvent event : list){
                    VariableBinding[] values = event.getColumns();
                    if(values == null) continue;
                    String name = values[1].getVariable().toString();//name
                    String cpu = values[4].getVariable().toString();//cpu
                    String memory = values[5].getVariable().toString();//memory
                    String path = values[2].getVariable().toString();//path
                    System.out.println("name--->"+name+"  cpu--->"+cpu+"  memory--->"+memory+"  path--->"+path);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(transport!=null)
                    transport.close();
                if(snmp!=null)
                    snmp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //服务器系统服务集合
    public static void collectService() {
        TransportMapping transport = null ;
        Snmp snmp = null ;
        CommunityTarget target;
        String[] oids =
                {"1.3.6.1.4.1.77.1.2.3.1.1"};
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            target = new CommunityTarget();
            target.setCommunity(new OctetString("public"));
            target.setRetries(2);
            target.setAddress(GenericAddress.parse("udp:127.0.0.1/161"));
            target.setTimeout(8000);
            target.setVersion(SnmpConstants.version2c);
            TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
                @Override
                public PDU createPDU(Target arg0) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }

                @Override
                public PDU createPDU(MessageProcessingModel messageProcessingModel) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }
            });
            OID[] columns = new OID[oids.length];
            for (int i = 0; i < oids.length; i++)
                columns[i] = new OID(oids[i]);
            @SuppressWarnings("unchecked")
            List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
            if(list.size()==1 && list.get(0).getColumns()==null){
                System.out.println(" null");
            }else{
                for(TableEvent event : list){
                    VariableBinding[] values = event.getColumns();
                    if(values == null) continue;
                    String name = values[0].getVariable().toString();//name
                    System.out.println("名称--->"+getChinese(name));//中文乱码，需要转为utf-8编码
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(transport!=null)
                    transport.close();
                if(snmp!=null)
                    snmp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //服务器端口集合
    public static void collectPort() {
        TransportMapping transport = null ;
        Snmp snmp = null ;
        CommunityTarget target;
        String[] TCP_CONN = {"1.3.6.1.2.1.6.13.1.1", //status
                "1.3.6.1.2.1.6.13.1.3"}; //port

        String[] UDP_CONN = {"1.3.6.1.2.1.7.5.1.2"};
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            target = new CommunityTarget();
            target.setCommunity(new OctetString("public"));
            target.setRetries(2);
            target.setAddress(GenericAddress.parse("udp:127.0.0.1/161"));
            target.setTimeout(8000);
            target.setVersion(SnmpConstants.version2c);
            TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
                @Override
                public PDU createPDU(Target arg0) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }

                @Override
                public PDU createPDU(MessageProcessingModel messageProcessingModel) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }
            });
//获取TCP 端口
            OID[] columns = new OID[TCP_CONN.length];
            for (int i = 0; i < TCP_CONN.length; i++)
                columns[i] = new OID(TCP_CONN[i]);
            @SuppressWarnings("unchecked")
            List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
            if(list.size()==1 && list.get(0).getColumns()==null){
                System.out.println(" null");
            }else{
                for(TableEvent event : list){
                    VariableBinding[] values = event.getColumns();
                    if(values == null) continue;
                    int status = Integer.parseInt(values[0].getVariable().toString());
                    System.out.println("status--->"+status+"   TCP_port--->"+values[1].getVariable().toString());
                }
            }
//获取udp 端口
            OID[] udpcolumns = new OID[UDP_CONN.length];
            for (int i = 0; i < UDP_CONN.length; i++)
                udpcolumns[i] = new OID(UDP_CONN[i]);
            @SuppressWarnings("unchecked")
            List<TableEvent> udplist = tableUtils.getTable(target, udpcolumns, null, null);
            if(udplist.size()==1 && udplist.get(0).getColumns()==null){
                System.out.println(" null");
            }else{
                for(TableEvent event : udplist){
                    VariableBinding[] values = event.getColumns();
                    if(values == null) continue;
                    String name = values[0].getVariable().toString();//name
                    System.out.println("UDP_port--->"+name);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(transport!=null)
                    transport.close();
                if(snmp!=null)
                    snmp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getChinese(String octetString){
        if(octetString == null || "".equals(octetString)
                || "null".equalsIgnoreCase(octetString)) return "";
        try{
            String[] temps = octetString.split(":");
            byte[] bs = new byte[temps.length];
            for(int i=0;i<temps.length;i++)
                bs[i] = (byte)Integer.parseInt(temps[i],16);
            return new String(bs,"GB2312");
        }catch(Exception e){
            return null;
        }
    }

}