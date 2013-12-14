package com.controle.beans;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.controle.dao.ClienteDAO;
import com.controle.dao.CompraDAO;
import com.controle.dao.FornecedorDAO;
import com.controle.dao.ProdutoDAO;
import com.controle.dao.VendaDAO;
import com.controle.model.Cliente;
import com.controle.model.Compra;
import com.controle.model.Fornecedor;
import com.controle.model.Produto;
import com.controle.model.Venda;

@ManagedBean(name = "relatoriosBean")
public class RelatoriosBean {
	private List<Produto> produtos;
	private List<Venda> vendas;
	private int tipo;
	private Date dataInicial;
	private Date dataFinal;
	private StreamedContent pdf;
	private StreamedContent xls;
	private List<Cliente> clientes;
	private List<Compra> compras;
	private List<Fornecedor> fornecedores;

	public RelatoriosBean() {

	}

	void gerarRelatorio(int modelo) {
		try {
			String reportPath = "";
			JasperPrint print = null;
			JasperReport report = null;

			switch (tipo) {
			case 1:
				produtos = new ProdutoDAO().listarTodos();
				reportPath = FacesContext.getCurrentInstance()
						.getExternalContext()
						.getRealPath("/reports/produtos.jrxml");
				report = JasperCompileManager.compileReport(reportPath);
				print = JasperFillManager.fillReport(report, null,
						new JRBeanCollectionDataSource(produtos));
				break;

			case 2:
				try {
					DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
					vendas = new VendaDAO().buscarPorDatas(
							sourceFormat.format(dataInicial),
							sourceFormat.format(dataFinal));

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				reportPath = FacesContext.getCurrentInstance()
						.getExternalContext()
						.getRealPath("/reports/vendas.jrxml");
				report = JasperCompileManager.compileReport(reportPath);
				print = JasperFillManager.fillReport(report, null,
						new JRBeanCollectionDataSource(vendas));
				break;
			case 3:
				clientes = new ClienteDAO().listarClientes();
				reportPath = FacesContext.getCurrentInstance()
						.getExternalContext()
						.getRealPath("/reports/clientes.jrxml");
				report = JasperCompileManager.compileReport(reportPath);
				print = JasperFillManager.fillReport(report, null,
						new JRBeanCollectionDataSource(clientes));
				break;
			case 4:
				try {
					DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
					compras = new CompraDAO().buscarPorDatas(
							sourceFormat.format(dataInicial),
							sourceFormat.format(dataFinal));

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				reportPath = FacesContext.getCurrentInstance()
						.getExternalContext()
						.getRealPath("/reports/compras.jrxml");
				report = JasperCompileManager.compileReport(reportPath);
				print = JasperFillManager.fillReport(report, null,
						new JRBeanCollectionDataSource(compras));
				break;
			case 5:
				fornecedores = new FornecedorDAO().listaFornecedor();
				reportPath = FacesContext.getCurrentInstance()
						.getExternalContext()
						.getRealPath("/reports/fornecedores.jrxml");
				report = JasperCompileManager.compileReport(reportPath);
				print = JasperFillManager.fillReport(report, null,
						new JRBeanCollectionDataSource(fornecedores));
				break;

			}

			if (modelo == 0) {

				String filePath = FacesContext.getCurrentInstance()
						.getExternalContext()
						.getRealPath("/reports/relatorio.pdf");

				JasperExportManager.exportReportToPdfFile(print, filePath);

			} else {

				String filePath = FacesContext.getCurrentInstance()
						.getExternalContext()
						.getRealPath("/reports/relatorio.xls");

				JRXlsExporter exporter = new JRXlsExporter();

				exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
						filePath);

				exporter.exportReport();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StreamedContent getPdf() {
		gerarRelatorio(0);

		InputStream stream = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/reports/relatorio.pdf");
		pdf = new DefaultStreamedContent(stream, "application/pdf ",
				"relatorio.pdf");
		return pdf;
	}

	public StreamedContent getXls() {
		gerarRelatorio(1);

		InputStream stream = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/reports/relatorio.xls");
		xls = new DefaultStreamedContent(stream, "application/xls ",
				"relatorio.xls");
		return xls;
	}

	public void setXls(StreamedContent xls) {
		this.xls = xls;
	}

	public void setPdf(StreamedContent pdf) {
		this.pdf = pdf;

	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

}
