

$(document).ready(function(){
	   $("#waitingtab").click(function(event){
		   $('#donetab').removeClass('current');
		   $('#waitingtab').addClass('current');
		   
		   $('.done_task').hide();
		   $('.waiting_task').show();
		   event.preventDefault();
	   });
	   
	   $("#donetab").click(function(event){
		   $('#waitingtab').removeClass('current');
		   $('#donetab').addClass('current');
		   
		   $('.waiting_task').hide();
		   $('.done_task').show();
		   event.preventDefault();
		   
		   
	   });
	   
	   $('.done_task').hide();
});
