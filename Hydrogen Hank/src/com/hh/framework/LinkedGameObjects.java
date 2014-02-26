package com.hh.framework;


import java.util.Iterator;
import java.util.LinkedList;
/**
 * 
 * @author Charlie Beckwith
 * @comments subclass of LinkedList to implement a sorting algorithm and potentially other features. 
 *
 */
public class LinkedGameObjects extends LinkedList<GameObject> {

	private static final long serialVersionUID = -6346273558859680661L;

	/**
	 * sort LinkedGameObjects LinkedList. Organizes a hierarchy for painting purposes. 
	 * 
	 */
	public void sort() {
		LinkedList<GameObject> background = new LinkedList<GameObject>();
		LinkedList<GameObject> middleground = new LinkedList<GameObject>();
		LinkedList<GameObject> foreground = new LinkedList<GameObject>();
		LinkedList<GameObject> hud = new LinkedList<GameObject>();
		LinkedList<GameObject> toplevel = new LinkedList<GameObject>();
		
		for (Iterator<GameObject> ll = this.iterator(); ll.hasNext();) {
			GameObject go = ll.next();
			if(go.getLayer() == ObjectLayer.background){
				background.add(go);
				ll.remove();
			}else if(go.getLayer() == ObjectLayer.middleground){
				middleground.add(go);
				ll.remove();
			}else if(go.getLayer() == ObjectLayer.foreground){
				foreground.add(go);
				ll.remove();
			}else if(go.getLayer() == ObjectLayer.hud){
				hud.add(go);
				ll.remove();
			}else if(go.getLayer() == ObjectLayer.toplevel){
				toplevel.add(go);
				ll.remove();
			}
		}
		
		for(Iterator<GameObject> bg = background.iterator(); bg.hasNext();){
			GameObject go = bg.next();
			this.add(go);
			bg.remove();
		}
		for(Iterator<GameObject> mg = middleground.iterator(); mg.hasNext();){
			GameObject go = mg.next();
			this.add(go);
			mg.remove();
		}
		for(Iterator<GameObject> fg = foreground.iterator(); fg.hasNext();){
			GameObject go = fg.next();
			this.add(go);
			fg.remove();
		}
		for(Iterator<GameObject> hd = hud.iterator(); hd.hasNext();){
			GameObject go = hd.next();
			this.add(go);
			hd.remove();
		}
		for(Iterator<GameObject> tl = toplevel.iterator(); tl.hasNext();){
			GameObject go = tl.next();
			this.add(go);
			tl.remove();
		}
		
		background = null;
		middleground = null;
		foreground = null;
		hud = null;
		toplevel = null;

	}

}
