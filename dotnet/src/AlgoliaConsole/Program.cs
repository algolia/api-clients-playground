/*
* Copyright (c) 2018 Algolia
* http://www.algolia.com/
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/

using Algolia.Search;
using Algolia.Search.Clients;
using Algolia.Search.Models.Batch;
using Algolia.Search.Models.Enums;
using Algolia.Search.Models.Search;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace AlgoliaConsole
{
    class Program
    {
        private static string _apiKey;
        private static string _appKey;

        static async Task Main(string[] args)
        {
            InitKeys();

            // Init the client
            SearchClient client = new SearchClient(_appKey, _apiKey);

            // Init index
            SearchIndex index = client.InitIndex("AlgoliaDotnetConsole");

            // Push data from Json
            using (StreamReader re = File.OpenText("Datas/Actors.json"))
            using (JsonTextReader reader = new JsonTextReader(re))
            {
                JArray batch = JArray.Load(reader);
                var ret = await index.SaveObjectsAsync(batch);
                ret.Wait();
            }

            // Get data 
            var actor = await index.GetObjectAsync<Actor>("551486310");
            Console.WriteLine(actor.ToString());

            // Search
            var search = await index.SearchAsync<Actor>(new Query("monica"));
            Console.WriteLine(search.Hits.ElementAt(0).ToString());
            Environment.Exit(0);
        }

        static void InitKeys()
        {
            if (String.IsNullOrEmpty(Environment.GetEnvironmentVariable("ALGOLIA_APPLICATION_ID")))
            {
                Console.WriteLine("Please set the following environment variable : ALGOLIA_APPLICATION_ID");
                Environment.Exit(1);
            }

            if (String.IsNullOrEmpty(Environment.GetEnvironmentVariable("ALGOLIA_ADMIN_API_KEY")))
            {
                Console.WriteLine("Please set the following environment variable : ALGOLIA_ADMIN_API_KEY");
                Environment.Exit(1);
            }

            _appKey = Environment.GetEnvironmentVariable("ALGOLIA_APPLICATION_ID");
            _apiKey = Environment.GetEnvironmentVariable("ALGOLIA_ADMIN_API_KEY");
        }

    }
}
