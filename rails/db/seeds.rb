# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)
Contact.delete_all
Contact.clear_index!
records = JSON.parse(File.read("#{Rails.root}/db/contacts.json"))
records.each do |record|
  Contact.create record
end
Contact.reindex!
