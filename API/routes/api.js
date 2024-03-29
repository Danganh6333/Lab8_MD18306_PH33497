const express = require("express");
var router = express.Router();

const Orders = require("../models/Order");

router.post("/add-order", async (req, res) => {
  try {
    const data = req.body;
    const newOrder = new Orders({
      order_code: data.order_code,
      id_user: data.id_user,
    });
    const result = await newOrder.save(); //Thêm vào database
    if (result) {
      //Nếu thêm thành công result !null trả về dữ liệu
      res.json({
        status: 200,
        messenger: "Thêm thành công",
        data: result,
      });
    } else {
      //Nếu thêm không thành công result null,thông báo không thành công
      res.json({
        status: 200,
        messenger: "Thêm không thành công",
        data: [],
      });
    }
  } catch (error) {
    console.log(error);
  }
});
router.get("/get-list-order", async (req, res) => {
  try {
    const { id_user } = req.query;
    const result = await Orders.find({ id_user: id_user }); //Thêm vào database
    if (result) {
      //Nếu thêm thành công result !null trả về dữ liệu
      res.json({
        status: 200,
        messenger: "Thêm thành công",
        data: result,
      });
    } else {
      //Nếu thêm không thành công result null,thông báo không thành công
      res.json({
        status: 200,
        messenger: "Lỗi,thêm không thành công",
        data: null,
      });
    }
  } catch (error) {
    console.log(error);
  }
});
router.delete('/delete-order/:order_code',async(req,res)=>{
    try {
      const {order_code} = req.params
      const result = await Orders.findOneAndDelete({order_code:order_code});
      console.log(order_code);
      console.log(result);
      if (result) {
        //Nếu thêm thành công result !null trả về dữ liệu
        res.json({
          status: 200,
          messenger: "Xóa thành công",
          data: result,
        });
      } else {
        //Nếu thêm không thành công result null,thông báo không thành công
        res.json({
          status: 200,
          messenger: "Lỗi,xóa không thành công",
          data: null,
        });
      }
    } catch (error) {
      
    }
})
