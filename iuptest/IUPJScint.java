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
public interface IUPJScint extends Library
{

    String dll = "iup_scintilla";
    IUPJScint inst = Native.loadLibrary(dll, IUPJScint.class);

    void IupScintillaOpen();
    Pointer IupScintilla();

}
