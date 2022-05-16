$("#placeOrder").click(function (){
    loadCustomerComboData();
    loadItemComboData();
});

function loadCustomerComboData(){
    $("#custChombo").empty();
    $("#custChombo").append($("<option></option>").attr("value", 0).text("Select Ids"));
    var count=1;
   $.ajax({
       url:"customer?option=GETAll",
       method:"GET",
       success:function (res){
           for (let customer of res.data){
               $("#custChombo").append($("<option></option>").attr("value", count).text(customer.id));
                count++;
           }
       },
       error:function (ob, textStatus, error) {
           alert(textStatus);
       }
   })
}

$("#custChombo").click(function () {
    var cusId = $("#custChombo option:selected").text();
    var address = $("#orderAddress").val();
    var contact = $("#orderTelephoneNo").val();
    var name = $("#orderCustName").val();

    $.ajax({
        url: "customer?option=GETAll",
        method: "GET",
        success:function (res){
            for (const customer of res.data){
                if(customer.id==cusId){

                    name=customer.name;
                    address=customer.address;
                    contact=customer.contact;

                    $("#orderAddress").val(address);
                    $("#orderTelephoneNo").val(contact);
                    $("#orderCustName").val(name);
                }
            }
        }
    })
});

function loadItemComboData(){
    console.log("Enter")
    $("#itemChombo").empty();
    $("#itemChombo").append($("<option></option>").attr("value", 0).text("Select Code"));
    var count=1;
    $.ajax({
        url:"item?option=GETAll",
        method:"GET",
        success:function (res){
            console.log("AAAAAAAA")
            for (let item of res.data){
                $("#itemChombo").append($("<option></option>").attr("value", count).text(item.code));
                count++;
            }
        },
        error:function (ob, textStatus, error) {
            alert(textStatus);
        }
    })
}

$("#itemChombo").click(function () {
    var code = $("#itemChombo option:selected").text();
    var name = $("#orderItemName").val();
    var price = $("#orderUnitPrice").val();
    var qtyOnHand = $("#orderQtyOnHand").val();

    $.ajax({
        url: "item?option=GETAll",
        method: "GET",
        success:function (res){
            for (const item of res.data){
                if(item.code==code){

                    name=item.name;
                    price=item.price;
                    qtyOnHand=item.qtyOnHand;

                    $("#orderItemName").val(name);
                    $("#orderUnitPrice").val(price);
                    $("#orderQtyOnHand").val(qtyOnHand);
                }
            }
        }
    })
});


let itemCode;
let subTotal;
let discount;

$("#orderTable").empty();

function loadItemData() {

    let itemCode = $("#itemChombo option:selected").text();
    let itemName = $("#orderItemName").val();
    let itemPrice = $("#orderUnitPrice").val();
    let itemQty = $("#orderQtyOnHand").val();
    let itemOrderQty = $("#orderOrderQty").val();

    let total = itemPrice * itemOrderQty;

    let avalilableQty = itemQty - itemOrderQty;

    $("#orderTable").append("<tr>" +
        "<td>" + itemCode + "</td>" +
        "<td>" + itemName + "</td>" +
        "<td>" + itemPrice + "</td>" +
        "<td>" + avalilableQty + "</td>" +
        "<td>" + itemOrderQty + "</td>" +
        "<td>" + total + "</td>" +
        `<td><button id="btnItemCartDelete" type="button" class="btn-sm btn-danger">Delete</button></td>` +
        "</tr>");

    calculateDiscount();

}
function calculateDiscount() {
    if (tot >= 10000) {
        discount = $("#discountCmb").val((tot / 100) * 20);
        $("#subToal").val(tot - discount);
    } else if (tot >= 8000 && tot < 9999) {
        discount = $("#discountCmb").val((tot / 100) * 15);
        $("#subToal").val(tot - discount);
    } else if (tot >= 6000 && tot < 7999) {
        discount = $("#discountCmb").val((tot / 100) * 10);
        $("#subToal").val(tot - discount);
    } else if (tot >= 2000 && tot < 5999) {
        discount = $("#discountCmb").val((tot / 100) * 5);
        $("#subToal").val(tot - discount);
    } else {
        $("#discountCmb").val("00");
    }
}

$("#addToCart").click(function () {
    var duplicate = false;
    for (var i = 0; i < $("#orderTable tr").length; i++) {

        if ($("#itemChombo option:selected").text() == $("#orderTable tr").children(':nth-child(1)')[i].innerText) {
            duplicate = true;
        }
    }
    if (duplicate != true) {

        loadItemData();
        minusQty($("#orderOrderQty").val());
        manageTotal($("#orderOrderQty").val() * $("#orderUnitPrice").val());

    } else if (duplicate == true) {

        manageQuantity(tableRowCount.children(':nth-child(5)').text(), $("#orderOrderQty").val());
        $(tableRowCount).children(':nth-child(4)').text($("#orderQtyOnHand").val());
        $(tableRowCount).children(':nth-child(5)').text($("#orderOrderQty").val());

        updateManageTotal(tableRowCount.children(':nth-child(6)').text(), $("#orderOrderQty").val() * $("#orderUnitPrice").val());
        $(tableRowCount).children(':nth-child(6)').text($("#orderOrderQty").val() * $("#orderUnitPrice").val());

    }

    $("#orderTable>tr").click('click', function () {

        tableRowCount = $(this);

        let itemCode = $(this).children(":eq(0)").text();
        let itemName = $(this).children(":eq(1)").text();
        let unitPrice = $(this).children(":eq(2)").text();
        let available = $(this).children(":eq(3)").text();
        let qty = $(this).children(":eq(4)").text();
        let total = $(this).children(":eq(5)").text();

        $("#itemChombo option:selected").text(itemCode);
        $("#orderItemName").val(itemName);
        $("#orderUnitPrice").val(unitPrice);
        $("#orderOrderQty").val(qty);
        $("#orderQtyOnHand").val(available);

    });
});

var tot = 0;

function manageTotal(amount) {
    tot += amount;
    $("#total").val(tot);
    calculateDiscount();

    let A = $("#total").val();
    let B = $("#discountCmb").val();

    $("#subToal").val(A - B);
}

function updateManageTotal(prvTotal, nowTotal) {
    tot -= prvTotal;
    tot += nowTotal;

    $("#total").val(tot);
    calculateDiscount();

    let A = $("#total").val();
    let B = $("#discountCmb").val();

    $("#subToal").val(A - B);
}

function manageBalence() {
    let A = $("#subToal").val();
    let B = $("#cash").val();

    $("#balance").val(B - A);
}

function manageQuantity(prevQty, nowQty) {
    var prevQty = parseInt(prevQty);
    var nowQty = parseInt(nowQty);
    var availableQty = parseInt($("#orderQtyOnHand").val());

    availableQty += prevQty;
    availableQty -= nowQty;

    $("#orderQtyOnHand").val(availableQty);
}

function minusQty(orderQty) {
    var minusQty = parseInt(orderQty);
    var manageQty = parseInt($("#orderQtyOnHand").val());

    manageQty = manageQty - minusQty;

    $("#orderQtyOnHand").val(manageQty);
}

function manageBalence() {
    let A = $("#subToal").val();
    let B = $("#cash").val();

    $("#balance").val(B - A);
}

$("#btnPurchase").click(function () {
   // placeOrder();
    //pushOrderDetails();
    clearCustomerData();
    manageBalence();
    clearItemData();
    $("#orderTable").empty();
    //generateOrderID();
    tot = 0;
});



$("#orderTable").on('click', '#btnItemCartDelete', function () {
    $(this).closest('tr').remove();
    $('#discountCmb,#total').val("");
    clearItemData();

});

function clearItemData() {
    $('#itemChombo,#orderItemName,#orderUnitPrice,#orderQtyOnHand,#orderOrderQty,orderId,orderDate').val("");
}

function clearCustomerData() {
    $('#custChombo,#orderCustName,#orderTelephoneNo,#orderAddress,#orderDate').val("");
}

$("#clear").click(function () {
    $('#total,#discountCmb,#subToal,#cash,#balance').val("");
});

