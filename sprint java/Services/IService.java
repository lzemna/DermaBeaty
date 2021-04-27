/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author user
 * @param <L>
 */
public interface IService<L> {
    public void ajouter(L l);
    public void supprimer(L l);
    public void modifier(L l);
    public List<L> afficher();
}
