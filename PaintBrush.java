/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintbrush;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;//to perform a specific task or action 
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;//notified whenever there is a change in state of mouse
import java.awt.event.MouseMotionListener;//to track the movement of the mouse
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 *
 * @author Admin
 */
public abstract class PaintBrush implements ActionListener,MouseListener,MouseMotionListener{
//they are used as we want to drag and make the required shape that is desired
    /**
     * @param args the command line arguments
     */
    int mousex=0,mousey=0;//this is the mouse starting coordinates in starting
    int prevx=0,prevy=0;// the prev coordinates are set to 0;
    int a,b,c,d;
    JFrame f;//construct the jframe of the paintbrush
    JMenuBar mbar;//construct the jmenubar status
    JButton pencil,line,rect,oval,frect,foval,polygon,spline,fill,pick,eraser,text,ok;
    //1.Pencil 2.Line 3.Rectangle 4.Oval 5.       6.    7.Polygon 8.    9.Fill 10.Pick 11.Eraser 12.Text 13.Ok 
    boolean pencilop=false,lineop=false,rectop=false,ovalop=false,frectop=false,fovalop=false,polygonop=false,splineop=false,fillop=false,pickop=false,eraserop=false,textop=false,okop=false; 
    // set all the flag to 0 ie false
   JMenu file,edit,format,help;
   // this will create the menu in jmenubar
   JMenuItem fnew,open,save,saveAs,page,print,exit;
   // the menu item that will be below the jmenu file
   JMenuItem undo,cut,copy,paste,delete,find,findnext,selectall,timedate;
    // the menu item that will be below the jmenu edit
   JMenuItem wordwrap,font;
    // the menu item that will be below the jmenu format
   JMenuItem about,topics;
    // the menu item that will be below the jmenu help
     JPanel drawpanel,status;
   //It is used to create a new JPanel with a double buffer and a flow layout.
     JColorChooser colchoose;
// 	It is used to create a color chooser panel with white color initially.
      JLabel xcor=new JLabel("X-Coordinate:");//Creates a jlabel with a specified text
      JLabel ycor=new JLabel("Y-Coordinate:");
      JTextField textf=new JTextField("Enter your Text here!!!",20);
      JTextField xtf=new JTextField(5);
      JTextField ytf=new JTextField(5);
      
    Color colpick=new Color(0,0,0); // default color red green blue
    Color statuscol=new Color(0,200,200);
    String xCoor ="    "; // x and y coordinates 
    String yCoor ="    ";
    JFrame fcolor,ftext; // frame for color and text
    JPanel textp;        // panel for text
    JComboBox textfont=new JComboBox(); // will contain all text
    JLabel size=new JLabel("Size");    //label for the size
    JTextField textsize=new JTextField("14",10);  //textsize
    JLabel sizee=new JLabel("Eraser Size");
    JTextField erasersize=new JTextField("10",5); // eraser size
    
    public PaintBrush()
{
f=new JFrame("Paint Brush");
ftext=new JFrame("Enter Text");
ftext.setSize(300,200);
textp=new JPanel();
ok=new JButton("OK");
ok.addActionListener(this);
ftext.add(textp);
mbar=new JMenuBar();
f.setJMenuBar(mbar);
status=new JPanel();
status.setBounds(48,600,800,30);
status.setBackground(statuscol);
status.add(sizee);
status.add(erasersize);
status.add(xcor);
xtf.setEditable(false);
status.add(xtf);
status.add(ycor);
ytf.setEditable(false);
status.add(ytf);
textp.setLayout(null);
textf.setBounds(0,0,285,30);
textp.add(textf);
GraphicsEnvironment ge = GraphicsEnvironment.
                getLocalGraphicsEnvironment();
                String[] fonts = ge.getAvailableFontFamilyNames();
textfont = new JComboBox(fonts);
textfont.setBounds(0,30,284,30);
textp.add(textfont);
size.setBounds(114,60,30,30);
textp.add(size);
textsize.setBounds(142,65,30,20);
textp.add(textsize);
ok.setBounds(114,90,58,30);
textp.add(ok);
textf.setEditable(false);
f.add(status);
drawpanel=new JPanel();
drawpanel.setBounds(48,0,800,600);
drawpanel.setBackground(Color.white);
drawpanel.addMouseMotionListener(this);
drawpanel.addMouseListener(this);
f.addMouseListener(this);
f.addMouseMotionListener(this);
colchoose=new JColorChooser();
fcolor=new JFrame("Pick Color");
fcolor.add(colchoose);
fcolor.setSize(650,400);
pencil=new JButton(new ImageIcon("pencil.jpg"));
fill=new JButton(new ImageIcon("fill.jpg"));
line=new JButton(new ImageIcon("line.jpg"));
rect=new JButton(new ImageIcon("rectangle.jpg"));
frect=new JButton(new ImageIcon("frect.jpg"));
oval=new JButton(new ImageIcon("oval.jpg"));
foval=new JButton(new ImageIcon("foval.jpg"));
pick=new JButton(new ImageIcon("pick.jpg"));
eraser=new JButton(new ImageIcon("eraser.jpg"));
text=new JButton(new ImageIcon("text.jpg"));
pencil.setBounds(0,0,24,24);
fill.setBounds(24,0,24,24);
fill.addActionListener(this);
line.setBounds(0,24,24,24);
rect.setBounds(24,24,24,24);
oval.setBounds(0,48,24,24);
pick.setBounds(24,48,24,24);
eraser.setBounds(0,72,24,24);
frect.setBounds(24,72,24,24);
foval.setBounds(0,96,24,24);
text.setBounds(24,96,24,24);
pick.addActionListener(this);
pencil.addActionListener(this);
rect.addActionListener(this);
frect.addActionListener(this);
oval.addActionListener(this);
foval.addActionListener(this);
line.addActionListener(this);
eraser.addActionListener(this);
text.addActionListener(this);
f.add(drawpanel);
f.add(pencil);
f.add(fill);
f.add(line);
f.add(rect);
f.add(oval);
f.add(pick);
f.add(eraser);
f.add(frect);
f.add(foval);
f.add(text);
file=new JMenu("File");
file.setMnemonic(KeyEvent.VK_F);
edit=new JMenu("Edit");
edit.setMnemonic(KeyEvent.VK_E);
format=new JMenu("Format");
format.setMnemonic(KeyEvent.VK_O);
help=new JMenu("Help");
help.setMnemonic(KeyEvent.VK_H);
fnew=new JMenuItem("New");
fnew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
fnew.addActionListener(this);
open=new JMenuItem("Open");
open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
open.addActionListener(this);
save=new JMenuItem("Save");
save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
save.addActionListener(this);
saveAs=new JMenuItem("SaveAs");
saveAs.addActionListener(this);
page=new JMenuItem("Page Setup");
page.addActionListener(this);
print=new JMenuItem("Print");
print.addActionListener(this);
exit=new JMenuItem("Exit");
exit.addActionListener(this);
undo=new JMenuItem("Undo");
undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
undo.addActionListener(this);
cut=new JMenuItem("Cut");
cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
cut.addActionListener(this);
copy=new JMenuItem("Copy");
copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
copy.addActionListener(this);
paste=new JMenuItem("Paste");
paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
paste.addActionListener(this);
delete=new JMenuItem("Delete");
delete.addActionListener(this);
find=new JMenuItem("Find");
find.addActionListener(this);
findnext=new JMenuItem("Find Next");
findnext.addActionListener(this);
selectall=new JMenuItem("Select All");
selectall.addActionListener(this);
timedate=new JMenuItem("Time/Date");
timedate.addActionListener(this);
wordwrap=new JMenuItem("Word Wrap");
wordwrap.addActionListener(this);
font=new JMenuItem("Font");
font.addActionListener(this);
about=new JMenuItem("About PaintBrush");
about.addActionListener(this);
topics=new JMenuItem("Help topics");
topics.addActionListener(this);
file.add(fnew);
file.add(open);
file.add(save);
file.add(saveAs);
file.addSeparator();
file.add(page);
file.add(print);
file.addSeparator();
file.add(exit);
edit.add(undo);
edit.addSeparator();
edit.add(cut);
edit.add(copy);
edit.add(paste);
edit.add(delete);
edit.addSeparator();
edit.add(find);
edit.add(findnext);
edit.addSeparator();
edit.add(selectall);
edit.add(timedate);
format.add(wordwrap);
format.add(font);
help.add(topics);
help.addSeparator();
help.add(about);
mbar.add(file);
mbar.add(edit);
mbar.add(format);
mbar.add(help);
f.setSize(864,690);
f.setLayout(null);
f.setVisible(true);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
    
 

    
    
    
    
    
public static void main(String[] args) {
        // TODO code application logic here
        new PaintBrush() {
            public void setMainColor()
{
if(pickop==true){
colpick=colchoose.getColor();
}
else
colpick=Color.black;
}   
    

public void rectoperation(MouseEvent e)
{
Graphics g  = drawpanel.getGraphics();
g.setColor(colpick);
setMainColor();
g.setColor(colpick);
if(rectop==true)
{
g.drawRect(a,b,c-a,d-b);
}
}
public void fillrectoperation(MouseEvent e)
{
Graphics g  = drawpanel.getGraphics();
g.setColor(colpick);
setMainColor();
g.setColor(colpick);
if(frectop==true)
{
g.fillRect(a,b,c-a,d-b);
}
}
public void ovaloperation(MouseEvent e)
{
Graphics g  = drawpanel.getGraphics();
g.setColor(colpick);
setMainColor();
g.setColor(colpick);
if(ovalop==true)
{
g.drawOval(a,b,c-a,d-b);
}
}
public void fillovaloperation(MouseEvent e)
{
Graphics g  = drawpanel.getGraphics();
g.setColor(colpick);
setMainColor();
g.setColor(colpick);
if(fovalop==true)
{
g.fillOval(a,b,c-a,d-b);
}
}
public void eraseroperation(MouseEvent e)
{
int erasers=Integer.parseInt(erasersize.getText());
Graphics g  = drawpanel.getGraphics();
g.setColor(Color.white);
Graphics2D g2 = (Graphics2D) g;
g2.setStroke(new BasicStroke(erasers));
if(eraserop==true)
{
if (mouseHasMoved(e))
{
mousex = e.getX();
mousey = e.getY();
g2.drawLine(prevx,prevy,mousex,mousey);
prevx = mousex;
prevy = mousey;
}
}
}
public void filloperation(MouseEvent e)
{
Graphics g  = drawpanel.getGraphics();
g.setColor(colpick);
setMainColor();
g.setColor(colpick);
if(fillop==true)
{
drawpanel.setBackground(colpick);
}
}
public void textoperation(MouseEvent e)
{
Graphics g  = drawpanel.getGraphics();
if(textop==true)
{
int texts;
texts=Integer.parseInt(textsize.getText());
String msg=textf.getText();
String item = (String) textfont.getSelectedItem();
g.setFont(new Font(item,Font.PLAIN,texts));
g.drawString(msg,prevx,prevy);
textf.setEditable(false);
}
}

            public void penciloperation(MouseEvent e)
{
if(pencilop==true)
{
Graphics g  = drawpanel.getGraphics();
g.setColor(colpick);
setMainColor();
g.setColor(colpick);
if (mouseHasMoved(e))
{
mousex = e.getX();
mousey = e.getY();
                                System.out.println("mousex =="+mousex+" mousey= "+mousey);
g.drawLine(prevx,prevy,mousex,mousey);
prevx = mousex;
prevy = mousey;
}
}
}
public void lineoperation(MouseEvent e)
{
Graphics g  = drawpanel.getGraphics();
g.setColor(colpick);
setMainColor();
g.setColor(colpick);
if(lineop==true)
{
if (mouseHasMoved(e))
{
mousex = e.getX();
mousey = e.getY();
g.drawLine(prevx,prevy,mousex,mousey);
}
}
}
             public boolean mouseHasMoved(MouseEvent e) {
                System.out.println("Mouse has moved mousex="+mousex+" mousey = "+mousey+" current x ="+e.getX()+" current y = "+e.getY());
                return (mousex != e.getX() || mousey != e.getY());
            }
            @Override
      public void actionPerformed(ActionEvent e) {
         /*  JMenuItem mi=(JMenuItem)ae.getSource();
if(mi==fnew)
{
}
else if(mi==open)
{
}
else if(mi==save)
{
}
else if(mi==saveAs)
{
}
else if(mi==exit)
{
System.exit(0);
}
else if(mi==undo)
{
}
else if(mi==cut)
{
}
else if(mi==copy)
{
}
else if(mi==paste)
{
}
*/
JButton be=(JButton)e.getSource();
if(be==pick)
{
fcolor.setVisible(true);
pickop=true;
}
else if(be==pencil)
{
pencilop=true;
fillop=false;
rectop=false;
ovalop=false;
lineop=false;
eraserop=false;
frectop=false;
fovalop=false;
textop=false;
}
else if(be==fill)
{
pencilop=false;
fillop=true;
rectop=false;
ovalop=false;
lineop=false;
eraserop=false;
frectop=false;
fovalop=false;
textop=false;
}
else if(be==rect)
{
pencilop=false;
fillop=false;
rectop=true;
ovalop=false;
lineop=false;
eraserop=false;
frectop=false;
fovalop=false;
textop=false;
}
else if(be==oval)
{
pencilop=false;
fillop=false;
rectop=false;
ovalop=true;
lineop=false;
eraserop=false;
frectop=false;
fovalop=false;
textop=false;
}
else if(be==line)
{
pencilop=false;
fillop=false;
rectop=false;
ovalop=false;
lineop=true;
eraserop=false;
frectop=false;
fovalop=false;
textop=false;
}
else if(be==eraser)
{
pencilop=false;
fillop=false;
rectop=false;
ovalop=false;
lineop=false;
eraserop=true;
frectop=false;
fovalop=false;
textop=false;
}
else if(be==frect)
{
pencilop=false;
fillop=false;
rectop=false;
ovalop=false;
lineop=false;
eraserop=false;
frectop=true;
fovalop=false;
textop=false;
}
else if(be==foval)
{
pencilop=false;
fillop=false;
rectop=false;
ovalop=false;
lineop=false;
eraserop=false;
frectop=false;
fovalop=true;
textop=false;
}
else if(be==text)
{
ftext.setVisible(true);
textf.setEditable(true);
pencilop=false;
fillop=false;
rectop=false;
ovalop=false;
lineop=false;
eraserop=false;
frectop=false;
fovalop=false;
textop=true;
}
if(be==ok)
{
ftext.setVisible(false);
}
            }
      public void updateMouseCoordinates(MouseEvent e)
{
if (e.getX() < 0) xCoor = "0";
else if (e.getX() > 800) xCoor = "800";
else
{
xCoor = String.valueOf(e.getX());
}

if (e.getY() < 0) xCoor = "0";
else if (e.getY() > 600) yCoor = "600";
else
{
yCoor = String.valueOf(e.getY());
}
xtf.setText(xCoor);
ytf.setText(yCoor);
}

            @Override
            public void mouseClicked(MouseEvent e) {
           updateMouseCoordinates(e);
Graphics g  = drawpanel.getGraphics();
g.setColor(colpick);
setMainColor();
g.setColor(colpick);
filloperation(e);
textoperation(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                prevx=e.getX();
prevy=e.getY();
updateMouseCoordinates(e);
a=e.getX();
b=e.getY();
                System.out.println("Prevx = "+prevx+" PrevY s "+prevy);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                updateMouseCoordinates(e);
rectoperation(e);
fillrectoperation(e);
ovaloperation(e);
fillovaloperation(e);
lineoperation(e);
penciloperation(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                updateMouseCoordinates(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                updateMouseCoordinates(e);
            }
            @Override
            public void mouseMoved(MouseEvent e)
{
updateMouseCoordinates(e);
}
            @Override
            public void mouseDragged(MouseEvent e) {
                updateMouseCoordinates(e);
                penciloperation(e);
                eraseroperation(e);
                
              c=e.getX();
              d=e.getY(); 
            }
           
        };
    }
    
}
