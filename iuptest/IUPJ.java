/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iuptest;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/**
 *
 * @author lulz
 */
public interface IUPJ extends Library
{
    String dll = "iup";
    IUPJ inst = Native.loadLibrary(dll, IUPJ.class);
    
    int IupOpen(int[] argc, String[] argv);
    void IupMessage(String title, String message);
    void IupClose();

    void IupSetGlobal(String name, String value);
    String IupGetGlobal(String name);

    void IupSetAttribute(Pointer handle, String name, String value);
    void      IupSetAttributeHandle(Pointer ih, String name, Pointer ih_named);
    Pointer IupScrollBox(Pointer child);
    Pointer  IupGetAttributeHandle(Pointer ih, String name);

    String    IupGetAttribute(Pointer ih, String name);
    
    Pointer  IupUser       ();
    
    Pointer   IupButton     (String title, String action);
    Pointer IupHbox(Pointer... child);
    Pointer IupVbox(Pointer... child);
    Pointer IupText(String action);
    Pointer IupDialog(Pointer child);
    Pointer IupFlatButton(String title);
    Pointer IupProgressBar();
    Pointer IupDatePick();
    Pointer IupCalendar();
    Pointer IupColorbar();
    Pointer IupGauge();
    Pointer IupColorBrowser();
    Pointer IupLabel(String s);
    int IupShowXY(Pointer ih, int x, int y);
    int IupMainLoop();
    String IupGetClassName(Pointer ih);
    String IupGetClassType(Pointer ih);
    Pointer IupDial(String orientation);

    int       IupPopup         (Pointer ih, int x, int y);

    Pointer IupMenu(Pointer... child);
    Pointer IupItem(String title, String action);

    ICallback IupGetCallback (Pointer ih, String name);
    ICallback IupSetCallback (Pointer ih, String name, ICallback func);

    Callback IupSetCallback (Pointer ih, String name, Callback func);
    
    /*
    Pointer IupFill ();
    Pointer IupSpace();

    Pointer IupRadio(Pointer child);
    Pointer IupVbox(Pointer child, ...);
    Pointer IupVboxv(Pointer *children);
    Pointer IupZbox(Pointer child, ...);
    Pointer IupZboxv(Pointer *children);
    Pointer IupHbox(Pointer child, ...);
    Pointer IupHboxv(Pointer *children);

    Pointer IupNormalizer(Pointer ih_first, ...);
    Pointer IupNormalizerv(Pointer *ih_list);

    Pointer IupCbox(Pointer child, ...);
    Pointer IupCboxv(Pointer *children);
    Pointer IupSbox(Pointer child);
    Pointer IupSplit(Pointer child1, Pointer child2);
    Pointer IupScrollBox(Pointer child);
    Pointer IupFlatScrollBox(Pointer child);
    Pointer IupGridBox(Pointer child, ...);
    Pointer IupGridBoxv(Pointer *children);
    Pointer IupExpander(Pointer child);
    Pointer IupDetachBox(Pointer child);
    Pointer IupBackgroundBox(Pointer child);

    Pointer IupFrame(Pointer child);
    Pointer IupFlatFrame(Pointer child);

    Pointer IupImage(int width, int height, const unsigned char *pixmap);
    Pointer IupImageRGB(int width, int height, const unsigned char *pixmap);
    Pointer IupImageRGBA(int width, int height, const unsigned char *pixmap);

    Pointer IupItem(const char* title, const char* action);
    Pointer IupSubmenu(const char* title, Pointer child);
    Pointer IupSeparator();
    Pointer IupMenu(Pointer child, ...);
    Pointer IupMenuv(Pointer *children);

    Pointer IupButton(const char* title, const char* action);
    Pointer IupFlatButton(const char* title);
    Pointer IupFlatToggle(const char* title);
    Pointer IupDropButton(Pointer dropchild);
    Pointer IupFlatLabel(const char* title);
    Pointer IupFlatSeparator();
    Pointer IupCanvas(const char* action);
    Pointer IupDialog(Pointer child);
    Pointer IupUser();
    Pointer IupLabel(const char* title);
    Pointer IupList(const char* action);
    Pointer IupText(const char* action);
    Pointer IupMultiLine(const char* action);
    Pointer IupToggle(const char* title, const char* action);
    Pointer IupTimer();
    Pointer IupClipboard();
    Pointer IupProgressBar();
    Pointer IupVal(const char *type);
    Pointer IupTabs(Pointer child, ...);
    Pointer IupTabsv(Pointer *children);
    Pointer IupFlatTabs(Pointer first, ...);
    Pointer IupFlatTabsv(Pointer *children);
    Pointer IupTree();
    Pointer IupLink(const char* url, const char* title);
    Pointer IupAnimatedLabel(Pointer animation);
    Pointer IupDatePick();
    Pointer IupCalendar();
    Pointer IupColorbar();
    Pointer IupGauge();
    Pointer IupDial(const char* type);
    Pointer IupColorBrowser();
     */
    
    interface ICallback extends Callback
    {
        int invoke(Pointer p);
    }

    interface CB_IntIntInt extends Callback
    {
        int invoke(Pointer p, int lin, int col, int pos);
    }

    // int function(Ihandle *ih, int c, char *new_value);
    interface CB_TxtAction extends Callback
    {
        int invoke(Pointer p, int c, String new_value);
    }
    
    interface CB_Int extends Callback
    {
        int invoke(Pointer p, int c);
    }

    //int function(Ihandle *ih, int lin, int col, int pos);

    
}
