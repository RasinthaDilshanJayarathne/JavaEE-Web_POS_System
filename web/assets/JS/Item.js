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
        success:function (res){
            if (res.status==200){
                alert(res.message);
                loadAllItem();
            }else {
                alert(res.data);
            }
        },
        error: function (ob, textStatus, error) {
            alert(textStatus);
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
            for (const item of resp.data) {
                var row = `<tr><td>${item.code}</td><td>${item.name}</td><td>${item.price}</td><td>${item.qtyOnHand}</td></tr>`;
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
        success: function (res) {
            console.log(res);
            if (res.status == 200) {
                alert(res.message);
                loadAllCustomers();
            } else if (res.status == 400) {
                alert(res.data);
            } else {
                alert(res.data);
            }
        },
        error: function (ob, status, t) {
            console.log(ob);
            console.log(status);
            console.log(t);
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