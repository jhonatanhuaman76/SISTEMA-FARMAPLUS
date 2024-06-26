package com.farmacia.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.farmacia.entidad.Producto;
import com.farmacia.interfaces.IProductoDAO;
import com.farmacia.util.MySqlConexion;

public class ProductoDAO implements IProductoDAO {

	@Override
	public int registrarProducto(Producto c) {
		//Declarar una variable para el resultado
		int r = -1;
		
		//Declarar objeto para la conexion
		Connection cone = null;
		
		//Declarar objeto para manipular procedimiento almacenado
		CallableStatement cstm = null;
		try {
			cone = MySqlConexion.miConexion();
			
			//Preparar el callableStatement
			cstm = cone.prepareCall("{call SP_REGISTRAR_PRODUCTO(?,?,?,?,?,?,?,?)}");
			
			//Enviar los datos a cstm obtenidos por la memoria ram
			cstm.setInt(1, c.getNum_cate());
			cstm.setString(2, c.getNom_pro());
			cstm.setDouble(3, c.getPre_unit_compra());
			cstm.setDouble(4, c.getPre_unit_venta());
			cstm.setInt(5, c.getStock_min());
			cstm.setInt(6, c.getStock_max());
			cstm.setString(7, c.getPres());
			cstm.setInt(8, c.getM_control());
			
			//Ejecutamos el callablestatement
			r=cstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(cone!=null) cone.close();
				if(cstm!=null) cstm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return r;
	}

	@Override
	public int modificarProducto(Producto c) {
		//Declarar una variable para el resultado
		int r = -1;
		
		//Declarar objeto para la conexion
		Connection cone = null;
		
		//Declarar objeto para manipular procedimiento almacenado
		CallableStatement cstm = null;
		try {
			cone = MySqlConexion.miConexion();
			
			//Preparar el callableStatement
			cstm = cone.prepareCall("{call SP_MODIFICAR_PRODUCTO(?,?,?,?,?,?,?,?,?)}");
			
			//Enviar los datos a cstm obtenidos por la memoria ram
			cstm.setInt(1, c.getCod_pro());
			cstm.setInt(2, c.getNum_cate());
			cstm.setString(3, c.getNom_pro());
			cstm.setDouble(4, c.getPre_unit_compra());
			cstm.setDouble(5, c.getPre_unit_venta());
			cstm.setInt(6, c.getStock_min());
			cstm.setInt(7, c.getStock_max());
			cstm.setString(8, c.getPres());
			cstm.setInt(9, c.getM_control());
			
			//Ejecutamos el callablestatement
			r=cstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(cone!=null) cone.close();
				if(cstm!=null) cstm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return r;
	}

	@Override
	public int eliminarProducto(int cod_prod) {
		//Declarar una variable para el resultado
		int r = -1;
		
		//Declarar objeto para la conexion
		Connection cone = null;
		
		//Declarar objeto para manipular procedimiento almacenado
		CallableStatement cstm = null;
		try {
			cone = MySqlConexion.miConexion();
			
			//Preparar el callableStatement
			cstm = cone.prepareCall("{call SP_ELIMINAR_PRODUCTO(?)}");
			
			//Enviar los datos a cstm obtenidos por la memoria ram
			cstm.setInt(1, cod_prod);
			
			//Ejecutamos el callablestatement
			r=cstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(cone!=null) cone.close();
				if(cstm!=null) cstm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return r;
	}

	@Override
	public Producto buscarProducto(int cod_prod) {
		//Declaramos objeto cliente
		Producto c = new Producto();
		
		//Declarar objeto para la conexion
		Connection cone = null;
		
		//Declarar objeto para manipular procedimiento almacenado
		CallableStatement cstm = null;
		
		//Declarar objeto ResultSet que tiene el resultado del SELECT, para hacer listado
		ResultSet rs = null;
		try {
			cone = MySqlConexion.miConexion();
			
			//Preparar el callableStatement
			cstm = cone.prepareCall("{call SP_BUSCAR_PRODUCTO(?)}");
			
			//Enviar los datos a cstm obtenidos por la memoria ram
			cstm.setInt(1, cod_prod);
			
			//Ejecutamos el callablestatement
			rs=cstm.executeQuery();
			
			while(rs.next()) {
				//Llenamos el objeto cliente con los datos
				c.setCod_pro(rs.getInt(1));
				c.setNum_cate(rs.getInt(2));
				c.setNom_pro(rs.getString(3));
				c.setPre_unit_compra(rs.getDouble(4));
				c.setPre_unit_venta(rs.getDouble(5));
				c.setStock_min(rs.getInt(6));
				c.setStock_max(rs.getInt(7));
				c.setPres(rs.getString(8));
				c.setM_control(rs.getInt(9));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(cone!=null) cone.close();
				if(cstm!=null) cstm.close();
				if(rs!=null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return c;
	}

	@Override
	public ArrayList<Producto> listadoProducto() {
		//Declaramos la lista de los clientes
		ArrayList<Producto> data = new ArrayList<Producto>();
		
		//Declarar objeto para la conexion
		Connection cone = null;
		
		//Declarar objeto para manipular procedimiento almacenado
		CallableStatement cstm = null;
		
		//Declarar objeto ResultSet que tiene el resultado del SELECT, para hacer listado
		ResultSet rs = null;
		
		try {
			cone = MySqlConexion.miConexion();
			
			//Preparar el callableStatement
			cstm = cone.prepareCall("{call SP_LISTAR_PRODUCTO()}");
			
			//Ejecutamos el callablestatement, enviar lo que tiene cstm a rs
			rs=cstm.executeQuery();
			
			//Llenamos la lista con los clientes de la base de datos
			while(rs.next()) {
				Producto c = new Producto();
				c.setCod_pro(rs.getInt(1));
				c.setNum_cate(rs.getInt(2));
				c.setNom_pro(rs.getString(3));
				c.setPre_unit_compra(rs.getDouble(4));
				c.setPre_unit_venta(rs.getDouble(5));
				c.setStock_min(rs.getInt(6));
				c.setStock_max(rs.getInt(7));
				c.setPres(rs.getString(8));
				c.setM_control(rs.getInt(9));
				
				data.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(cone!=null) cone.close();
				if(cstm!=null) cstm.close();
				if(rs!=null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return data;
	}

	@Override
	public ArrayList<Producto> reporteGeneralProductos() {
		//Declaramos la lista de los clientes
		ArrayList<Producto> data = new ArrayList<Producto>();
		
		//Declarar objeto para la conexion
		Connection cone = null;
		
		//Declarar objeto para manipular procedimiento almacenado
		CallableStatement cstm = null;
		
		//Declarar objeto ResultSet que tiene el resultado del SELECT, para hacer listado
		ResultSet rs = null;
		
		try {
			cone = MySqlConexion.miConexion();
			
			//Preparar el callableStatement
			cstm = cone.prepareCall("{call SP_REPORTE_GENERAL_PRODUCTOS()}");
			
			//Ejecutamos el callablestatement, enviar lo que tiene cstm a rs
			rs=cstm.executeQuery();
			
			//Llenamos la lista con los clientes de la base de datos
			while(rs.next()) {
				Producto c = new Producto();
				c.setCod_pro(rs.getInt(1));
				c.setNom_pro(rs.getString(2));
				c.setNom_cate(rs.getString(3));
				c.setPre_unit_venta(rs.getDouble(4));
				c.setStock_min(rs.getInt(5));
				c.setStock_max(rs.getInt(6));
				c.setStock_total(rs.getInt(7));
				c.setPres(rs.getString(8));
				c.setTotal_ventas(rs.getInt(9));
				
				data.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(cone!=null) cone.close();
				if(cstm!=null) cstm.close();
				if(rs!=null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return data;
	}

	@Override
	public ArrayList<Producto> estadisticasPrecio() {
		//Declaramos la lista de los clientes
		ArrayList<Producto> data = new ArrayList<Producto>();
		
		//Declarar objeto para la conexion
		Connection cone = null;
		
		//Declarar objeto para manipular procedimiento almacenado
		CallableStatement cstm = null;
		
		//Declarar objeto ResultSet que tiene el resultado del SELECT, para hacer listado
		ResultSet rs = null;
		
		try {
			cone = MySqlConexion.miConexion();
			
			//Preparar el callableStatement
			cstm = cone.prepareCall("{call SP_ESTADISTICAS_PRECIO()}");
			
			//Ejecutamos el callablestatement, enviar lo que tiene cstm a rs
			rs=cstm.executeQuery();
			
			//Llenamos la lista con los clientes de la base de datos
			while(rs.next()) {
				Producto c = new Producto();
				c.setNom_mayor(rs.getString(1));
				c.setNom_menor(rs.getString(2));
				c.setPvmayor(rs.getDouble(3));
				c.setPvmenor(rs.getDouble(4));
				c.setPvprom(rs.getDouble(5));
				
				data.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(cone!=null) cone.close();
				if(cstm!=null) cstm.close();
				if(rs!=null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return data;
	}

}
