/*
Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
For licensing, see license.txt or http://cksource.com/ckfinder/license
*/

CKFinder.customConfig = function( config )
{
	// Define changes to default configuration here.
	// For the list of available options, check:
	// http://docs.cksource.com/ckfinder_2.x_api/symbols/CKFinder.config.html

	// Sample configuration options:
	 config.uiColor = '#BDE31E';
	 config.language = 'vi';
	// config.removePlugins = 'basket';
	 config.language = 'zh-cn';
	 config.baseFloatZIndex = 19900;
	 config.filebrowserBrowseUrl = '/shophoa/ckfinder/ckfinder.html';
    config.filebrowserImageBrowseUrl = '/shophoa/ckfinder/ckfinder.html?type=Images';
    config.filebrowserFlashBrowseUrl = '/shophoa/ckfinder/ckfinder.html?type=Flash';
    config.filebrowserUploadUrl = '/shophoa/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
    config.filebrowserImageUploadUrl = '/shophoa/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
    config.filebrowserFlashUploadUrl = '/shophoa/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';

};
