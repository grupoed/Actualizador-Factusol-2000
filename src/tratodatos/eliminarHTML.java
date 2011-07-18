/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tratodatos;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Stack;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

public class eliminarHTML extends HTMLEditorKit.ParserCallback
{

  private StringBuffer stringBuffer;

  private Stack<IndexType> indentStack;

  public static class IndexType
  {
    public String type;
    public int counter; // used for ordered lists
    public IndexType(String type)
    {
      this.type = type;
      counter = 0;
    }
  }

  public eliminarHTML()
  {
    stringBuffer = new StringBuffer(); 
    indentStack = new Stack<IndexType>();
  }

  public static String convert(String html)
  {
    eliminarHTML parser = new eliminarHTML();
    Reader in = new StringReader(html);
    try
    {
      // the HTML to convert
      parser.parse(in);
    }
    catch (Exception e)
    {
     // log.severe(e.getMessage());
    }
    finally
    {
      try
      {
        in.close();
      }
      catch (IOException ioe)
      {
        // this should never happen
      }
    }
    return parser.getText();    
  }

  public void parse(Reader in) throws IOException
  {
    ParserDelegator delegator = new ParserDelegator();
    // the third parameter is TRUE to ignore charset directive
    delegator.parse(in, this, Boolean.TRUE);
  }

    @Override
  public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos)
  {
   // log.info("StartTag:" + t.toString());
    if (t.toString().equals("p"))
    {
      if (stringBuffer.length() > 0 && !stringBuffer.substring(stringBuffer.length() - 1).equals("\n"))
      {
        newLine();
      }
      newLine();
    }    
    else if (t.toString().equals("ol"))
    {
      indentStack.push(new IndexType("ol"));
      newLine();
    }    
    else if (t.toString().equals("ul"))
    {
      indentStack.push(new IndexType("ul"));
      newLine();
    }       
    else if (t.toString().equals("li"))
    {
      IndexType parent = indentStack.peek();
      if (parent.type.equals("ol"))
      {
        String numberString = "" + (++parent.counter) + ".";
        stringBuffer.append(numberString);
        for (int i = 0; i < (4 - numberString.length()); i++)
        {
          stringBuffer.append(" ");
        }
      }
      else
      {
        stringBuffer.append("*   ");
      }
      indentStack.push(new IndexType("li"));
    }  
    else if (t.toString().equals("dl"))
    {
      newLine();
    }
    else if (t.toString().equals("dt"))
    {
      newLine();
    }       
    else if (t.toString().equals("dd"))
    {
      indentStack.push(new IndexType("dd"));
      newLine();
    }       
  }

  private void newLine()
  {
    stringBuffer.append("\n");
    for (int i = 0; i < indentStack.size(); i++)
    {
      stringBuffer.append("    ");
    }    
  }

    @Override
  public void handleEndTag(HTML.Tag t, int pos)
  {
    //log.info("EndTag:" + t.toString());
    if (t.toString().equals("p"))
    {
      newLine();
    }   
    else if (t.toString().equals("ol"))
    {
      indentStack.pop();
      newLine();
    }    
    else if (t.toString().equals("ul"))
    {
      indentStack.pop();
      newLine();
    }    
    else if (t.toString().equals("li"))
    {
      indentStack.pop();
      newLine();
    }     
    else if (t.toString().equals("dd"))
    {
      indentStack.pop();
    }      
  }

    @Override
  public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos)
  {
   // log.info("SimpleTag:" + t.toString());
    if (t.toString().equals("br"))
    {
      newLine();
    }
  }

    @Override
  public void handleText(char[] text, int pos)
  {
    //log.info("Text:" + new String(text));
    stringBuffer.append(text);
  }

  public String getText()
  {
    return stringBuffer.toString();
  } 

}