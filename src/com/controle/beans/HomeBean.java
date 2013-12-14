package com.controle.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.controle.dao.CompraDAO;
import com.controle.dao.VendaDAO;
import com.controle.model.Compra;
import com.controle.model.Venda;

@ManagedBean(name = "homeBean")
public class HomeBean {
	PieChartModel pieChartModel;
	CartesianChartModel categoryModel;
	List<Compra> compras;
	List<Venda> vendas;
	private int valorMaximo;

	public HomeBean() {
		compras = new CompraDAO().listarCompras();
		vendas = new VendaDAO().listarVendas();
		createPieChartModel();
		createCategorymodel();

	}

	private void createCategorymodel() {
		categoryModel = new CartesianChartModel();

		ChartSeries vendasSerie = new ChartSeries();
		vendasSerie.setLabel("Vendas");

		List<Venda> vendasSetembro = new VendaDAO().buscarPorDatas(
				"20/08/2013", "01/10/2013");
		List<Venda> vendasOutubro = new VendaDAO().buscarPorDatas("20/09/2013",
				"01/11/2013");
		List<Venda> vendasNovembro = new VendaDAO().buscarPorDatas(
				"20/10/2013", "01/12/2013");
		List<Venda> vendasDezembro = new VendaDAO().buscarPorDatas(
				"20/11/2013", "01/01/2014");
		
		double somaSetembro = somaVendas(vendasSetembro);
		double somaOutubro = somaVendas(vendasOutubro);
		double somaNovembro = somaVendas(vendasNovembro);
		double somaDezembro = somaVendas(vendasDezembro);

		int maior1 = (int) Math.ceil(Math.max(somaSetembro, somaOutubro));
		int maior2 = (int) Math.ceil(Math.max(somaNovembro, somaDezembro));
		int maior = Math.max(maior1, maior2);
		if (maior <= 0) {
			maior = 200;
		}
		setValorMaximo(maior);

		vendasSerie.set("Setembro", somaSetembro);
		vendasSerie.set("Outubro", somaOutubro);
		vendasSerie.set("Novembro", somaNovembro);
		vendasSerie.set("Dezembro", somaDezembro);

		categoryModel.addSeries(vendasSerie);

	}

	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	public CartesianChartModel getCategoryModel() {
		return categoryModel;
	}

	private void createPieChartModel() {
		pieChartModel = new PieChartModel();
		pieChartModel.set("Vendas", somaVendas(this.vendas));
		pieChartModel.set("Compras", somaCompras());
	}

	private double somaCompras() {
		double sum = 0;
		for (Compra compra : compras) {
			sum += compra.getValor();
		}
		return sum;
	}

	private double somaVendas(List<Venda> vendasSoma) {
		double sum = 0;
		for (Venda venda : vendasSoma) {
			sum += venda.getValor();
		}
		return sum;
	}

	public int getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(int valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

}
