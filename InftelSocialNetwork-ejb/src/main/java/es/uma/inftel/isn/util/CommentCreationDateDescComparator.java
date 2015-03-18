/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.inftel.isn.util;

import java.util.Comparator;

/**
 *
 * @author miguel
 */
public class CommentCreationDateDescComparator implements Comparator<Comment> {

    @Override
    public int compare(Comment c1, Comment c2) {
        return c2.getCreationDate().compareTo(c1.getCreationDate());
    }
    
}
