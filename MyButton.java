import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*; 
public class MyButton extends JButton implements ActionListener{
   private GridCalendar model;
   private AppointmentsDatabase events;
   private JFrame frame;
   private boolean OK=false;
   private String label;
   private String key="0";
   private  JList list;
   private boolean selected;
   
   public MyButton(String l,AppointmentsDatabase e){
      super(l);
      label=l;
      events=e;      
      addActionListener(this);
   }
   
   public MyButton(String label){
      super(label);
      OK=true;
      addActionListener(this);
   }
   
   public void actionPerformed(ActionEvent e){
      frame=new JFrame("");
      
      Container cp=frame.getContentPane();
      cp.setLayout(new BorderLayout());
      JPanel panel1=new JPanel(new FlowLayout());
      JPanel panel2=new JPanel(new BorderLayout());
      JPanel panel3=new JPanel(new FlowLayout());
      JLabel title=new JLabel("Takimet: ");
      JButton add=new AddButton("Shto",this);
     
      JButton view=new ViewButton("Shiko",this);
      JButton edit=new EditButton("Ndrysho",this);
     
      String[] array=new String[100];
      if(!OK){
         for(int i=0;i<100;i++){
            if(events.getAppointment()[i]!=null)
               array[i]=events.getAppointment()[i].getHour()+ " "+
                       events.getAppointment()[i].getEvent();
            else{}
         }
      }
      else{
         for(int i=0;i<1;i++){
            array[i]="";
         }
      }

      list=new JList(array);
      JScrollPane sp=new JScrollPane( list);
      JButton delete=new DeleteButton("Fshij",this);
   
      
      list.addListSelectionListener(
         new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e){
               if (!e.getValueIsAdjusting()){ 
                  if(list.getSelectedValue()!=null){
                     key=list.getSelectedValue().toString();
                     selected=list.isSelectionEmpty();}
               }
            
            }
         }
         );
      
      selected=list.isSelectionEmpty();
     
      list.setVisibleRowCount(10);
      
      panel1.add(title, FlowLayout.LEFT);
      cp.add(panel1,BorderLayout.NORTH);
   
      panel2.add(sp);
      cp.add(panel2);
      
      panel2. addMouseListener(
         new MouseAdapter() {
              
         
            @Override
                public void mousePressed(MouseEvent e) {
               list.clearSelection();
            }
         
            @Override
                public void mouseReleased(MouseEvent e) {
               list.clearSelection();
            }
         }
     
         );   
      panel3.add(add);  
      panel3.add(view);
      panel3.add(edit);
      panel3.add(delete);
            
      cp.add(panel3,BorderLayout.SOUTH);
      
      
      frame.setSize(450,350);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);   
   }
   
   public JFrame getFrame(){
      return frame;
   }
   
   public AppointmentsDatabase getApp(){
      return events;
   }
   
   public String getLabel(){
      return label;
   }
   
   public void update(AppointmentsDatabase a){
      super.setBorder(BorderFactory.createLineBorder(new Color(210,78,130), 2));
      events=a;
   }
   public void update2(AppointmentsDatabase a){
      super.setBorder(new JButton().getBorder());
   
      events=a;
   }
   
   public String getKey(){
      return key;
   
   }
   public boolean getSelected(){
      return selected;
   }
    
}