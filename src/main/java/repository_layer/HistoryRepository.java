package repository_layer;

import config.DBconnection;
import model_layer.OrderHistoryDetailItem;
import model_layer.OrderHistoryItem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HistoryRepository {

    public List<OrderHistoryItem> getOrdersByCustomer(String customerID) {
        List<OrderHistoryItem> list = new ArrayList<>();

        String sql =
                "SELECT " +
                "o.orderID, " +
                "o.customerID, " +
                "o.shipperID, " +
                "ISNULL(s.name, N'Chưa xác nhận') AS shipperName, " +
                "o.orderDate, " +
                "o.shippedDate, " +
                "o.freight, " +
                "o.addressID, " +
                "ISNULL(ac.address, N'Không có địa chỉ') AS addressName, " +
                "o.paymentID, " +
                "ISNULL(mp.paymentMethod, N'Không rõ') AS paymentMethod, " +
                "o.amount, " +
                "ISNULL(STRING_AGG(sh.name, ', '), N'Không rõ shop') AS shopName, " +
                "o.status " +
                "FROM Orders o " +
                "LEFT JOIN Shipper s ON o.shipperID = s.shipperID " +
                "LEFT JOIN Address_Customer ac ON o.addressID = ac.addressID " +
                "LEFT JOIN Payment p ON o.paymentID = p.paymentID " +
                "LEFT JOIN Method_Payment mp ON p.payID = mp.payID " +
                "LEFT JOIN Order_Detail od ON o.orderID = od.orderID " +
                "LEFT JOIN Product pr ON od.productID = pr.productID " +
                "LEFT JOIN Shop sh ON pr.shopID = sh.shopID " +
                "WHERE o.customerID = ? " +
                "GROUP BY " +
                "o.orderID, o.customerID, o.shipperID, s.name, " +
                "o.orderDate, o.shippedDate, o.freight, " +
                "o.addressID, ac.address, o.paymentID, mp.paymentMethod, " +
                "o.amount, o.status " +
                "ORDER BY o.orderDate DESC, o.orderID DESC";

        try (
                Connection con = DBconnection.openConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, customerID);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderHistoryItem item = new OrderHistoryItem();

                    item.setOrderID(rs.getString("orderID"));
                    item.setCustomerID(rs.getString("customerID"));

                    item.setShipperID(rs.getString("shipperID"));
                    item.setShipperName(rs.getString("shipperName"));

                    Date orderDate = rs.getDate("orderDate");
                    if (orderDate != null) {
                        item.setOrderDate(orderDate.toLocalDate());
                    }

                    Date shippedDate = rs.getDate("shippedDate");
                    if (shippedDate != null) {
                        item.setShippedDate(shippedDate.toLocalDate());
                    }

                    item.setFreight(rs.getDouble("freight"));

                    item.setAddressID(rs.getString("addressID"));
                    item.setAddressName(rs.getString("addressName"));

                    item.setPaymentID(rs.getString("paymentID"));
                    item.setPaymentMethod(rs.getString("paymentMethod"));

                    item.setAmount(rs.getDouble("amount"));
                    item.setShopName(rs.getString("shopName"));
                    item.setStatus(rs.getString("status"));

                    item.setDetails(getOrderDetails(item.getOrderID()));

                    list.add(item);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<OrderHistoryDetailItem> getOrderDetails(String orderID) {
        List<OrderHistoryDetailItem> list = new ArrayList<>();

        String sql =
                "SELECT " +
                "od.orderID, " +
                "od.productID, " +
                "od.unitPrice, " +
                "od.quantity, " +
                "od.discount, " +
                "p.name AS productName " +
                "FROM Order_Detail od " +
                "LEFT JOIN Product p ON od.productID = p.productID " +
                "WHERE od.orderID = ?";

        try (
                Connection con = DBconnection.openConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, orderID);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderHistoryDetailItem item = new OrderHistoryDetailItem();

                    item.setOrderID(rs.getString("orderID"));
                    item.setProductID(rs.getString("productID"));
                    item.setProductName(rs.getString("productName"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setUnitPrice(rs.getDouble("unitPrice"));
                    item.setDiscount(rs.getDouble("discount"));

                    list.add(item);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}