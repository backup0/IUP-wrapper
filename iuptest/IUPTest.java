/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iuptest;

import com.sun.jna.*;
import com.sun.jna.ptr.ByReference;
import iuptest.IUPJ.ICallback;

import java.io.IOException;

/**
 *
 * @author proto
 */
public class IUPTest
{
    private interface Poi extends Library
    {
       String dll = "D:\\libz\\iup\\iup.dll";
       Poi inst = (Poi) Native.loadLibrary(dll, Poi.class);

       int IupOpen(int[] argc, String[] argv);
       void IupMessage(String title, String message);
       void IupClose();
    }

    private static void iup()
    {
        Native.DEBUG_LOAD = true;
        System.setProperty("jna.library.path", "D:\\libz\\iupvc\\iup");
        Native.setProtected(true);
        /*
        Poi p = Poi.inst;
        p.IupOpen(new int[]{0}, new String[]{});
        p.IupMessage("Hello world!", "Greetings! Hello? I don't know what ...");
        p.IupClose();
        */
        //System.load("D:\\libz\\iup\\iupgl.dll");

        //IUPJScint is = IUPJScint.inst;
        //IUPJGl igl = IUPJGl.inst;
        IUPJ ij = IUPJ.inst;

        ij.IupOpen(new int[]{0}, new String[]{});

        String eppe = "This is my TESt string, let's hope it worketh! Okays?";
        Pointer txt = ij.IupText(null);
        ij.IupSetAttribute(txt, "MULTILINE", "YES");
        ij.IupSetAttribute(txt, "FORMATTING", "YES");
        ij.IupSetAttribute(txt, "EXPAND", "YES");
        //

        Pointer fmtTag = ij.IupUser();

        ij.IupSetAttribute(fmtTag, "BGCOLOR", "255 128 64");
        ij.IupSetAttribute(fmtTag, "UNDERLINE", "SINGLE");
        ij.IupSetAttribute(fmtTag, "WEIGHT", "BOLD");
        ij.IupSetAttribute(fmtTag, "SELECTION", "1,2:1,6");
        //ij.IupSetAttributeHandle(txt, "ADDFORMATTAG_HANDLE", fmtTag);

        // CARET_CB
        //ij.IupSetCallback(txt, "CARET_CB", new CaretMove());
        ij.IupSetCallback(txt, "ACTION", new TxtAxtion());

        Pointer ih1 = ij.IupFlatButton("Button1");
        Pointer ih2 = ij.IupFlatButton("Button2");
        Pointer ih3 = ij.IupFlatButton("Button3");
        Pointer datePick = ij.IupDatePick();

        Pointer ih = ij.IupVbox(datePick, ih1, ih2, ih3, ij.IupLabel("Some label"), ij.IupLabel("xyz"),
                ij.IupLabel("I know"), ij.IupFlatButton("Another btn"), ij.IupButton("Btn", null),
                ij.IupLabel("A label"), ij.IupLabel("ebz"), ij.IupLabel("KnowZ"), ij.IupLabel("xxxY"));

        Pointer scroller = ij.IupScrollBox(ih);

        Pointer dlg = ij.IupDialog(txt);
        ij.IupSetAttribute(txt, "CARET", "1,1");


        ij.IupSetAttribute(dlg, "TITLE", "Simple Notepad");
        //ij.IupSetAttribute(dlg, "SIZE", "QUARTERxQUARTER");
        ij.IupSetAttribute(dlg, "SIZE", "HALFxHALF");

        w(ij.IupShowXY(dlg, 65535, 65535));
        ij.IupSetAttribute(dlg, "USERSIZE", null);

        ij.IupSetAttribute(txt, "INSERT", eppe);
        ij.IupSetAttributeHandle(txt, "ADDFORMATTAG", fmtTag);

        ij.IupSetAttribute(txt, "SELECTION", "1,2:1,6");
        w(ij.IupGetAttribute(txt, "SELECTEDTEXT"));

        w(ij.IupGetClassName(ih));
        w(ij.IupGetClassType(ih));

        /*
        ij.IupSetCallback(dlg, "CLOSE_CB", new Etc());

        ICallback icb = ij.IupGetCallback(dlg, "CLOSE_CB");
        if (icb == null)
        {
            w("Null");
        }
        else
        {
            w("Not null...");
        }
        */

        ij.IupMainLoop();

        //w(ih, " ", ih.);
        ij.IupClose();
    }

    private static class Etc implements ICallback
    {

        @Override
        public int invoke(Pointer p)
        {
            w(p.getString(0));
            return IUPVal.IUP_DEFAULT;
        }
    }

    private static class CaretMove implements IUPJ.CB_IntIntInt
    {
        @Override
        public int invoke(Pointer p, int lin, int col, int pos)
        {
            w("Caret move, @ ", pos);
            return IUPVal.IUP_DEFAULT;
        }
    }

    private static class TxtAxtion implements IUPJ.CB_TxtAction
    {
        @Override
        public int invoke(Pointer p, int c, String new_value)
        {
            w("- ", c, " (nv)- ", new_value);
            return IUPVal.IUP_IGNORE;
        }
    }

    public static void w(Object... t)
     {
          //if (DEV_MODE)
          {
               StringBuilder sb = new StringBuilder();
               for (Object s : t)
               {
                    sb.append(s);
               }
               //System.out.println(sb.toString().replace(",", "").replace(" ", ""));
               System.out.println(sb.toString());
          }
     }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        // TODO code application logic here
        //iup();
        Native.DEBUG_LOAD = true;
        System.setProperty("jna.library.path", "D:\\libz\\iupfullvc");
        Native.setProtected(true);

        //IM im = IM.inst;
        //IUPJIm img = IUPJIm.inst;

        /*
        StringByRef sbr = new StringByRef();
        sbr.setValue("Hellos ladiez");
        w(sbr.getValue());
        */

        /*
        w(im.imBinFileSetCurrentModule(2));

        IntByReference irb = new IntByReference();
        IM.ImBinMemoryFileName b = new IM.ImBinMemoryFileName();
        byte[] bytes = Files.readAllBytes(Paths.get("d:\\Documents\\bigloading.gif"));

        Pointer ptr = new Memory(bytes.length);
        ptr.write(0, bytes, 0, bytes.length);

        b.buffer = ptr;
        b.size = bytes.length;
        b.reallocate = 0;

        Pointer imgFile = im.imFileOpen(b, irb);
        w(irb.getValue(), " ", imgFile);

        IntByReference a = new IntByReference();
        StringByRef ax = new StringByRef();
        StringByRef bx = new StringByRef();
        ax.setValue("ax");
        bx.setValue("bx");

        im.imFileGetInfo(imgFile, ax, bx, new IntByReference());
        w(ax.getValue(), " ", bx.getValue());
        */

        /*
        byte[] bytes = "Well done!".getBytes();
        w("?? ", bytes.length);

        Pointer ptr = new Memory(bytes.length);
        ptr.write(0, bytes, 0, bytes.length);

        byte[] ebbe = ptr.getByteArray(0, bytes.length);

        w(((Memory) ptr).size(), ", ", (new String(ebbe)));
        ptr.
        */
        //ptr.
        /*

        //new Texto();

        IntByReference irb = new IntByReference();

        IUPJ i = IUPJ.inst;
        i.IupOpen(new int[]{0}, new String[]{});

        Pointer imFile = im.imFileOpen("d:\\Documents\\bigloading.gif", irb);
        //Pointer imFile = im.imFileOpen("D:\\Desktop\\Screenshot_12.jpg", irb);
        //Pointer imFile = im.imFileOpen("d:\\Documents\\viewer.png", irb);
        w(imFile.getInt(0));

        IntByReference irb2 = new IntByReference();
        Pointer imImg = im.imFileLoadBitmap(imFile, 0, irb2);
        // Alternatively - a shortcut.
        //Pointer imImg = im.imFileImageLoadBitmap("d:\\Documents\\bigloading.gif", 0, irb2);
        im.imFileClose(imFile);

        w(irb2.getValue(), " - ", imImg.getInt(0), imImg, " is bmp: ", im.imImageIsBitmap(imImg));

        Pointer ptr2 = img.IupImageFromImImage(imImg);
        w(ptr2);
        */

        //Pointer ptr2 = img.IupGetImageNativeHandle(imImg);

        /*
        String opx;
        Scanner scanner = new Scanner(System.in);
        do
        {
            try 
            {
                iup();
            }
            catch (Exception ex)
            {
                w("OOPS!!!");
                 Logger.getLogger(IUPTest.class.getName()).log(Level.SEVERE, null, ex);
            }            
            
            System.out.println("? ");
            opx = scanner.nextLine();
        
        } while (!"x".equalsIgnoreCase(opx));
        */
        new Texto();
    }

    public static class StringByRef extends ByReference
    {
        protected StringByRef(int dataSize)
        {
            super(4);
        }

        public StringByRef()
        {
            this(4);
        }

        public void setValue(String value)
        {
            w("Set val called");
            byte[] bytes = value.getBytes();
            Pointer ptr = new Memory(bytes.length + 1);
            ptr.setString(0, value);
            setPointer(ptr);
            getPointer();
        }
        public String getValue()
        {
            return getPointer().getString(0);
        }
    }
}
