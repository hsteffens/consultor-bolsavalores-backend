package br.furb.consultor.usuario;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import persistencia.Usuario;
import controle.UsuarioJpaController;

public class main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("BolsaValoresPU");
        Usuario usuario = new Usuario();
        usuario.setCarteiraClienteCollection(null);
        usuario.setCdCpf(new BigInteger("01234567891"));
        usuario.setCdInvestidor(null);
        usuario.setCdTransacao(null);
        usuario.setCdUsuario(1);
        usuario.setDsEmail("seuemail@seuprovedor.com");
        usuario.setDsNome("Hélinton Bichão");
        usuario.setDsProfissao("PUTO");
        usuario.setSugestaoCompraCollection(null);
        usuario.setSugestaoVendaCollection(null);
                
        UsuarioJpaController usuarioJpaController = new UsuarioJpaController(factory);
        
        try {
            usuarioJpaController.create(usuario);
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        factory.close();
    }
}
