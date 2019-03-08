source "https://rubygems.org"

gem "fastlane"
gem 'danger', '~> 5.16', '>= 5.16.1'
gem 'danger-android_lint'
gem 'danger-checkstyle_format'
plugins_path = File.join(File.dirname(__FILE__), 'fastlane', 'Pluginfile')
eval_gemfile(plugins_path) if File.exist?(plugins_path)
