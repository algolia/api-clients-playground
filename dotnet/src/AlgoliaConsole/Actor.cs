using Newtonsoft.Json;

namespace AlgoliaConsole
{
    public class Actor
    {
        public string Name { get; set; }

        public string ObjectID { get; set; }

        public int Rating { get; set; }

        [JsonProperty(PropertyName = "image_path")]
        public string ImagePath { get; set; }

        [JsonProperty(PropertyName = "alternative_name")]
        public string AlternativePath { get; set; }

        public override string ToString()
        {
            return $"Name: {Name}\r\nObjectId: {ObjectID}\r\nRating: {Rating}";
        }
    }
}