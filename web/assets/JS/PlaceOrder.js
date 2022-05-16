$("#placeOrder").click(function (){
    console.log("Hello")
    loadCustomerComboData();
});

function loadCustomerComboData(){
    console.log("Enter")
    var count=0;
   $.ajax({
       url:"customer?option=GETALL",
       method:"GET",
       success:function (res){
           console.log(res);
           for (const customer of res.data){
               $("#custChombo").append($("<option></option>").attr("value", count).text(customer.id));
                count++;
           }
       },
       error:function (ob, textStatus, error) {
           alert(textStatus);
       }
   })
}



/*
$("#custChombo").click(function () {

});*/
