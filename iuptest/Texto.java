package iuptest;

import com.sun.jna.Pointer;

import static iuptest.IUPTest.w;

public class Texto
{
    private final IUPJ _iup;
    private final Pointer _txt;
    private final Pointer _dlg;
    private final Pointer _menu;
    
    public Texto()
    {
        _iup = IUPJ.inst;
        _iup.IupOpen(new int[]{0}, new String[]{});
        _iup.IupSetGlobal("DEFAULTFONTSIZE", "10");

        _txt = _iup.IupText(null);
        setUpTxt();

        Pointer menu1 = _iup.IupItem("Item1", null);
        Pointer menu2 = _iup.IupItem("Item2", null);
        _menu = _iup.IupMenu(menu1, menu2);

        _dlg = _iup.IupDialog(_txt);
        _iup.IupSetAttribute(_dlg, "TITLE", "Simple Notepad");
        _iup.IupSetAttribute(_dlg, "SIZE", "HALFxHALF");
        _iup.IupShowXY(_dlg, 65535, 65535);

        // _iup.IupPopup(_menu, 1800, 1400); // - pop the menu, need to figure out where to put the stuff.

        _iup.IupMainLoop();
        _iup.IupClose();
    }
    
    private void setUpTxt()
    {
        _iup.IupSetAttribute(_txt, "MULTILINE", "YES");
        _iup.IupSetAttribute(_txt, "FORMATTING", "YES");
        _iup.IupSetAttribute(_txt, "EXPAND", "YES");
        _iup.IupSetAttribute(_txt, "BORDER", "NO");
        _iup.IupSetAttribute(_txt, "PADDING", "15x15");
        _iup.IupSetAttribute(_txt, "WORDWRAP", "YES");

        _iup.IupSetCallback(_txt, "K_ANY", getCB_K_ANY());
        _iup.IupSetCallback(_txt, "CARET_CB", getCB_CARET());
        /*
        _iup.IupSetCallback(_txt, "K_ANY", getCB_K_ANY());
        // undo
        _iup.IupSetCallback(_txt, "K_cZ", getCB_K_UNDO());
        // redo
        _iup.IupSetCallback(_txt, "K_cY", getCB_K_REDO());
        */

        // MODKEYSTATE <-- use that global var to check key combo.
    }

    private IUPJ.CB_IntIntInt getCB_CARET()
    {
        return (handle, lin, col, pos) ->
        {
            w();
            w("Caret @ ", pos);
            return IUPVal.IUP_CONTINUE;
        };
    }

    private IUPJ.CB_Int getCB_K_ANY()
    {
        return (handle, c) ->
        {
            String s = _iup.IupGetAttribute(_txt, "COUNT");
            w("Key pressed. ", (char) c, " @ ", s);
            return IUPVal.IUP_CONTINUE;
        };
    }

    private IUPJ.CB_Int getCB_K_UNDO()
    {
        return (handle, c) ->
        {
            w("Undo");
            return IUPVal.IUP_IGNORE;
        };
    }
    private IUPJ.CB_Int getCB_K_REDO()
    {
        return (handle, c) ->
        {
            w("Reso");
            return IUPVal.IUP_IGNORE;
        };
    }
}
