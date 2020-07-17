/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brain.report;

import brain.controller.DbConnect;
import java.awt.BorderLayout;
import java.sql.SQLException;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Brain
 */
public class ClsLoad_Report
{
    public static void Print_Report(String path,String querry,JFrame fr) throws JRException, ClassNotFoundException, SQLException, Exception
    {
        JasperDesign jd=JRXmlLoader.load(path);
        JRDesignQuery jrq=new JRDesignQuery();
        jrq.setText(querry);
        jd.setQuery(jrq);
        JasperReport jreP=JasperCompileManager.compileReport(jd);
        JasperPrint jpr=JasperFillManager.fillReport(jreP,null, DbConnect.connectDb());
        JRViewer jv=new JRViewer(jpr);
        fr.setLayout(new BorderLayout());
        fr.add(jv);
        fr.revalidate();
        fr.repaint();        
    }
    
}
