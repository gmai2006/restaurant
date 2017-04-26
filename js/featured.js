$(document).ready(function() {
	
	var timeout = null;
	
	var onclick = function(element) {
		$('#featured-content .act').removeClass('act').fadeOut();
		$('#feat' + element.attr('rel')).addClass('act').fadeIn();

		$('#featured-tabs li.act').removeClass('act');
		element.parent().addClass('act');
		
		if (timeout) {
			window.clearTimeout(timeout);
			timeout = window.setTimeout(ontime, 5000);
		}
		
		return false;
	};
	
	var ontime = function() {
		var licur = $('#featured-tabs li.act');
		var linext = licur.next();
		var element;
		
		if (linext.size() > 0) {
			element = linext.find('a');
		} else {
			element = $('#featured-tabs li:first a');
		}
		
		timeout = null;
		
		onclick(element);
		
		timeout = window.setTimeout(ontime, 5000);
	};
	
	$('#featured-tabs a').click(function() {
		return onclick($(this));
	});
	
	timeout = window.setTimeout(ontime, 5000);
});