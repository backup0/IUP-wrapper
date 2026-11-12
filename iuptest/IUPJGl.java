/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iuptest;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 *
 * @author lulz
 */
public interface IUPJGl extends Library
{
    String dll = "iupgl.dll";
    IUPJGl inst = Native.loadLibrary(dll, IUPJGl.class);

    //Pointer IupScintilla();

}
