package com.mrjaffesclass.apcs.todolist;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Roger Jaffe
 * @version 1.0
 */
public class DateRenderer extends DefaultTableCellRenderer {
    SimpleDateFormat formatter;
    
    public DateRenderer() {
      super(); 
      formatter = new SimpleDateFormat("E MM/d", Locale.ENGLISH);
    }

    @Override
    public void setValue(Object value) {
      value.toString();
      setText((value == null) ? "" : formatter.format(value).toString());
    }
}