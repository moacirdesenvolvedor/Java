package pos_java_jdbc.pos_java_jdbc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import dao.UserPosDAO;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class TesteBancoJdbc {

	@Test
	public void initBanco() { // metodo de insert

		UserPosDAO userPosDAO = new UserPosDAO();
		Userposjava userposjava = new Userposjava();

		userposjava.setNome("paulo");
		userposjava.setEmail("paulo@gmail.com");

		userPosDAO.salvar(userposjava);

	}

	@Test
	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<Userposjava> list = dao.listar();

			for (Userposjava userposjava : list) {
				System.out.println(userposjava);
				System.out.println("----------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initBuscar() {

		UserPosDAO dao = new UserPosDAO();

		try {
			Userposjava userposjava = dao.buscar(5L);

			System.out.println(userposjava);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void initAtualizar() {
		try {

			UserPosDAO dao = new UserPosDAO();

			Userposjava objetoBanco = dao.buscar(5L);

			objetoBanco.setNome("Nome mudado com metodo atualizar");

			dao.atualizar(objetoBanco);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initDeletar() {

		try {

			UserPosDAO dao = new UserPosDAO();
			dao.deletar(6L);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testeInsertTelefone() {

		Telefone telefone = new Telefone();
		telefone.setNumero("(87) 4445-4545");
		telefone.setTipo("Casa");
		telefone.setUsuario(16L);

		UserPosDAO dao = new UserPosDAO();
		dao.salvarTelefone(telefone);

	}

	@Test
	public void testeCarregaFonesUser() {

		UserPosDAO dao = new UserPosDAO();

		List<BeanUserFone> beanUserFones = dao.listaUserFone(16L);

		for (BeanUserFone beanUserFone : beanUserFones) {
			System.out.println(beanUserFone);
			System.out.println("---------------------------------------------");
		}

	}

	@Test
	public void testeDeleteUserFone() {

		UserPosDAO dao = new UserPosDAO();
		dao.deleteFonesPorUser(15L);

	}

	@Test
	public void teste() {

		try {

			// Data Limite com 50 anos
			Date dataLimite = new SimpleDateFormat("dd/MM/yyyy").parse("25/10/2068");

			// Zera as horas, minutos e segundos
			Calendar cal = Calendar.getInstance();
			
			// Data informada pelo usúario
			cal.setTime(new SimpleDateFormat("dd/MM/yyyy").parse("25/10/2068"));
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			// Add 50 anos
			//cal.add(Calendar.YEAR, 50);
			
			if (cal.getTime().compareTo(dataLimite) <= 0 ) {
				System.out.println("Data está OK");
			} else {
				System.out.println("Data passou do limite");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
