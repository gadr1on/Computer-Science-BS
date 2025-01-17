simpleStore.plugins.google = (function() {

	var storeProducts = verifyProducts = [];

	function getSpreadsheetData(s, verify, callback) {

		verify = typeof verify !== 'undefined' ? verify : false;

		var hostname = "https://spreadsheets.google.com";
		var format = "json";
		var spreadsheetURL = hostname + "/feeds/worksheets/" + s.spreadsheetID + "/public/full?alt=" + format;
		var mainsheetURL = hostname + "/feeds/list/" + s.spreadsheetID + "/od6/public/values?alt=" + format;
		var settingsSheetName = "Settings";
		var productsSheetName = ["Rum & Beer", "Snacks & Candy", "Fruits & Vegetables", "Meat", "Bread & Bakery", "Dessert"];
		var home = "http://localhost/food2go/";
		var categories = ["_rum","_candy", "_fruits", "_meat", "_bread", "_dessert"];
		var sheetIDs = {};

		function getSheetInfo (url, callback) {
			// Need to do this because od6 is default Google Sheet ID
			$.getJSON(url)
				.done(function(data) {

					var sheets = data.feed.entry;

					$(sheets).each(function(i, sheet) {

						var title = sheet.title.$t;
						var id = sheet.id.$t;
						var sheetID = id.substr(id.lastIndexOf('/') + 1);

						if(title == settingsSheetName) {
							sheetIDs.settingsSheetID = sheetID;
						}
						//window.location.href
						if(title == productsSheetName[0] && home+categories[0] == window.location.href) {
							sheetIDs.productsSheetID  = sheetID;
						}
            if(title == productsSheetName[1] && home+categories[1] == window.location.href) {
							sheetIDs.productsSheetID  = sheetID;
						}
						if(title == productsSheetName[2] && home+categories[2] == window.location.href) {
							sheetIDs.productsSheetID  = sheetID;
						}
						if(title == productsSheetName[3] && home+categories[3] == window.location.href) {
							sheetIDs.productsSheetID  = sheetID;
						}
						if(title == productsSheetName[4] && home+categories[4] == window.location.href) {
							sheetIDs.productsSheetID  = sheetID;
						}
						if(title == productsSheetName[5] && home+categories[5] == window.location.href) {
							sheetIDs.productsSheetID  = sheetID;
						}
					});
					callback(sheetIDs.settingsSheetID);
					loadProductData(sheetIDs.productsSheetID);
				});
		}

		function loadSiteSettings (id, callback) {

			var settingsSheetURL = hostname + "/feeds/list/" + s.spreadsheetID + "/" + id + "/public/values?alt=" + format;

			$.getJSON(settingsSheetURL)
				.done(function(data) {
					var data = data.feed.entry;
					var s = simpleStore.settings;

					if(data[0]) {

						var siteName = data[0].gsx$sitenametextorimagelink.$t;
						var columns = data[0].gsx$columns123.$t;

						if (siteName) {
							s.brand = siteName;
						}
						if (columns) {
							s.numColumns = columns;
						}

						simpleStore.setLayout(s);
					}
				});
		}

		function loadProductData (id) {

			var productsSheetURL = hostname + "/feeds/list/" + s.spreadsheetID + "/" + id + "/public/values?alt=" + format;

			// Get Main Sheet Products data
			$.getJSON(productsSheetURL)
				.done(function(data) {

					var productsData = data.feed.entry;

					// Build products
					$(productsData).each(function(i) {

						var options = this.gsx$options.$t;
						var setOptions = function(options) {
							var productOptions = [];
							if(options) {
								var opts = options.split(";").filter(function(el) {return el.length != 0});
								$(opts).each(function(i, option) {
									var opt = option.trim().split(":"),
										key = opt[0],
										val = opt[1],
										optObj = {};

									optObj[key] = val;
									productOptions.push(optObj);
								});
							}
							return productOptions;
						};

						// Get product values
						var product = {
							name : this.gsx$name.$t,
							price : this.gsx$price.$t,
							description : this.gsx$description.$t,
							options : setOptions(options),
							image : this.gsx$image.$t
						};

						if (verify) {
							verifyProducts.push(product);
						} else {
							storeProducts.push(product);
						}
					});
					callback();
				})
				.fail(function(data){
					if (verify) {
						var errorMsg = 'There was an error validating your cart.';
					} else {
						var errorMsg = 'Error loading spreadsheet data. Make sure the spreadsheet ID is correct.';
					}
					setTimeout(function(){ simpleStore.renderError(s, errorMsg); }, 1000);
				});
		}

		// Get Sheet data
		getSheetInfo(spreadsheetURL, loadSiteSettings);
	}

	function validatePrices(s, checkoutData) {
		verifyProducts = [];

		getSpreadsheetData(s, true, function() {
			//if(simpleStore.verifyCheckoutData(checkoutData, verifyProducts, true)) {
        		simpleStore.checkout(s, checkoutData);
		/*	} else {
				var errorMsg = 'There was an error validating your cart.';
				simpleStore.renderError(s, errorMsg);
			}*/
		});
	}

	return {
		init: function(callback) {
			var s = simpleStore.settings;

			// Clears out brand to allow for spreadsheet site name
			s.brand = "Food2Go";
			simpleStore.setLayout(s);

			getSpreadsheetData(s, false, function(){
				callback(storeProducts);
			});
		},
		validate: function(checkoutData) {
			validatePrices(simpleStore.settings, checkoutData);
		}
	};
})();
