$("#item").click(function (){
    loadAllItem();
})

$("#popItemBtnAdd").click(function () {
    let data = $("#itemTableForm").serialize();
    console.log(data)
    $.ajax({
        url: "item",
        method: "POST",
        data: data,
        success: function (resp) {
            alert(resp)
            loadAllItem();
        },
        error: function (ob, textStatus, error) {
            alert(textStatus);
            console.log(ob.responseText);
        }
    });
});

function loadAllItem() {
    $("#itemTable").empty();
    $.ajax({
        url: "item",
        method: "GET",
        /* dataType :"json",*/
        success: function (resp) {
            for (var i in resp) {
                var code = resp[i].code;
                var name = resp[i].name;
                var price = resp[i].price;
                var qtyOnHand = resp[i].qtyOnHand;

                var row = `<tr><td>${code}</td><td>${name}</td><td>${price}</td><td>${qtyOnHand}</td></tr>`;
                $("#itemTable").append(row);
            }
            bindClickEvents();
        }
    });
}

$("#btnItemDelete").click(function (){
    let itemCode = $("#txtItemCode").val();
    $.ajax({
        url:"item?code="+itemCode,
        method:"DELETE",
        //data :data,
        success : function (resp){
            console.log(resp);
            loadAllItem();
        }
    })
});

function bindClickEvents(){
    $("#itemTable>tr").click(function (){
        let code=$(this).children().eq(0).text();
        let name=$(this).children().eq(1).text();
        let price=$(this).children().eq(2).text();
        let qtyOnHand=$(this).children().eq(3).text();

        $("#txtItemCode").val(code);
        $("#txtItemName").val(name);
        $("#txtItemPrice").val(price);
        $("#txtItemQTYOnHand").val(qtyOnHand);
    });
}

$("#btnItemUpdate").click(function (){
    let fromData = $("#viewItemForm").serialize();
    $.ajax({
        url:"item?"+fromData,
        method:"PUT",
        //data: fromData,
        success:function (res){
            console.log(res)
            loadAllCustomer();
        }
    })
});